package com.hz.dafeiji.ai.user.modules.equipments;

import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.addtion.AddtionCollection;
import com.hz.dafeiji.ai.addtion.AddtionType;
import com.hz.dafeiji.ai.addtion.AddtionValue;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.property.PropertyModule;
import com.hz.dafeiji.cfg.equipment.EquipmentQurlityTemplet;
import com.hz.dafeiji.cfg.equipment.EquipmentQurlityTempletCfg;
import com.hz.dafeiji.cfg.equipment.EquipmentTemplet;
import com.hz.dafeiji.cfg.equipment.EquipmentTempletCfg;
import com.hz.dafeiji.cfg.manual.EquipExpCfg;
import java.util.*;

/**
 * user         valen
 * time         14-3-25 下午1:52
 * 装备模块
 */

public class EquipmentModule{

    final EquipmentDataProvider db;

    private Comparator<Equipment> equipmentComparator = null;


    /**
     * 背包里的装备
     */
    private Map<Long, Equipment> equipments;

    public EquipmentModule( String uname ){
        db = new EquipmentDataProvider( uname );
        equipments = db.getMapAll();

        equipmentComparator = new Comparator<Equipment>() {
            @Override
            public int compare(Equipment o1, Equipment o2) {
                if(o1.getQuality() > o2.getQuality()){
                    return 1;
                }else if(o1.getQuality() == o2.getQuality()){
                    return o1.getLevel() > o2.getLevel() ? -1 : 1;
                }else{
                    return -1;
                }
            }
        };
    }

    /**
     * 添加一件新装备
     * @param templetIds 要添加的道具模版ID列表, 逗号分割
     */
    public void addAll(String templetIds){
        for(String templetId : templetIds.split(",")){
            if(!templetId.equals("")){
                int equipTempleteId = Integer.valueOf(templetId);
                if(EquipmentTempletCfg.getEquipmentTempletById(equipTempleteId) != null){
                    Equipment equip = new Equipment(equipTempleteId);
                    equipments.put(equip.getId(), equip);
                    db.add(equip);
                }else{
                    throw new ClientException(ErrorCode.EQUIPMENT_TEMPLET_NOT_FOUND, "要添加的装备模版ID不存在,传递的模版ID:"+templetId);
                }
            }else{
                throw new ClientException(ErrorCode.PARAMETER_ERROR, "要添加的装备模版ID为空,传递参数:"+templetIds);
            }
        }
    }

    /**
     * 分解装备
     * @param equipIds 要分解的装备id列表,逗号分割
     * @param user USER对象用于扣除相关消耗品
     */
    public void splitEquip(String equipIds, User user){
        for(String id : equipIds.split(",")){
            if(!id.equals("")){
                long eid = Long.valueOf(id);
                Equipment equip = equipments.get(eid);
                if(checkEquipExsit(equip, eid, "分解装备", user)){
                    int energy = EquipExpCfg.getSplitExp(equip.getQuality(), equip.getLevel(), equip.getTemplet().getType());  //分解装备获取的能源

                    deleteEquip(equip);         //删除装备
                    user.getModuleManager().getPropertyModule().changeEnergy(energy);       //添加用户能源数据
                }
            }else{
                throw new ClientException(ErrorCode.PARAMETER_ERROR, "要分解的装备ID为空,传递参数:"+equipIds);
            }
        }
    }

    /**
     * 装备升级
     * @param equipId 要升级的装备ID
     * @param user USER对象用于扣除相关消耗品
     */
    public void levelUpEquip(long equipId, User user){
        Equipment equip = equipments.get(equipId);

        if(checkEquipExsit(equip, equipId, "升级装备", user)){
            EquipmentQurlityTemplet et = EquipmentQurlityTempletCfg.getEquipmentQurlityTempletById(equip.getQuality());
            if(equip.getLevel() >= et.getMaxLv()){
                throw new ClientException(ErrorCode.EQUIPMENT_LEVEL_OVER_LIMIT,"装备升级超出等级限制,品质:"+equip.getQuality()
                        +",当前等级:"+equip.getLevel()
                        +",上限:"+et.getMaxLv());
            }

            //计算升级装备需要的能源
            int energyCost = EquipExpCfg.getExp(equip.getQuality(), equip.getLevel(), equip.getTemplet().getType());
            PropertyModule propertyModule = user.getModuleManager().getPropertyModule();

            if(propertyModule.getEnergy() < energyCost){
                throw new ClientException(ErrorCode.USER_ENERGY_NOT_ENOUGH, "装备升级能源值不够,需要:"+energyCost
                        +",拥有:"+propertyModule.getEnergy());
            }

            //装备升级
            equip.levelUp();
            db.updateWithField(equip, EquipmentDataProvider.FIELD_LEVEL, equip.getLevel());

            //扣除能源
            propertyModule.changeEnergy(energyCost * -1);
        }
    }

    /**
     * 装备进阶
     * @param equipId 要进阶的装备ID
     * @param user USER对象用于扣除相关消耗品
     */
    public void upgradeEquip(long equipId, User user){
        Equipment equip = equipments.get(equipId);
        if(checkEquipExsit(equip, equipId, "进阶装备", user)){
            EquipmentQurlityTemplet et = EquipmentQurlityTempletCfg.getEquipmentQurlityTempletById(equip.getQuality());

            if(equip.getQuality() >= equip.getTemplet().getQualityMax()){
                throw new ClientException(ErrorCode.EQUIPMENT_UPGRADE_OVER_LIMIT, "进阶装备已经达到品质上限,当前阶级:"+equip.getQuality()
                        +",上限:"+equip.getTemplet().getQualityMax());
            }

            if(equip.getLevel() < et.getMaxLv()){
                throw new ClientException(ErrorCode.EQUIPMENT_LEVEL_UNDER_LIMIT, "进阶装备未达到等级要求,进阶装备等级:"
                        +equip.getLevel()+",要求等级:"+et.getMaxLv());
            }

            int costDraw = et.getAdvanceDraw();         //消耗图纸
            int costEquip = et.getAdvanceEquipment();   //消耗装备数量
            int costGold = et.getAdvanceGold();         //消耗金币
            int costJewel = et.getAdvanceJewel();       //消耗钻石

            PropertyModule propertyModule = user.getModuleManager().getPropertyModule();
            List<Equipment> deleteEquips = new ArrayList<>();
            if(costGold > 0 && costGold > propertyModule.getCash()){
                throw new ClientException(ErrorCode.USER_GOLD_NOT_ENOUGH, "进阶装备金币不够,需要:"+costGold+",拥有:"+propertyModule.getCash());
            }

            if(costJewel > 0 && costJewel > propertyModule.getDiamond()){
                throw new ClientException(ErrorCode.USER_DIAMOND_NOT_ENOUGH, "进阶装备钻石不够,需要:"+costJewel+",拥有:"+propertyModule.getDiamond());
            }

            if(costEquip > 0){
                deleteEquips = getEquipCountByArgs(et.getAdvanceEquipmentQue(), equip.getTemplet().getId());
                if(deleteEquips.size() < costEquip){
                    throw new ClientException(ErrorCode.EQUIPMENT_UPGRADE_EQUIP_NOT_ENOUGH
                            , "进阶需要的装备不够,需要品质"+et.getAdvanceEquipmentQue()+"的装备"+costEquip+"件,拥有:"+deleteEquips.size()+"件");
                }
            }

            //扣除金币
            if(costGold > 0){
                propertyModule.changeCash(costGold * -1);
            }

            //扣除钻石
            if(costJewel > 0){
                propertyModule.changeDiamond(costJewel * -1);
            }

            //删除消耗的装备
            for(Equipment de : deleteEquips){
                deleteEquip(de);            //删除被吃掉的装备
            }

            equip.upgradeQuality();
            db.updateWithField(equip, EquipmentDataProvider.FIELD_QUALITY, equip.getQuality());
        }
    }


    /**
     * 穿戴装备
     * @param equipId 要穿戴的装备Id
     */
    public void loadEquip(long equipId, User user){
        Equipment equip = equipments.get(equipId);
        if(checkEquipExsit(equip, equipId, "穿戴装备", user)){
            Equipment downEquip = getLoadedEquipByType(equip.getTemplet().getType());
            if(downEquip != null){      //该类型已经有装备穿戴上, 替换成传入的装备
                downEquip.setLoaded(0);
                db.updateWithField(downEquip, EquipmentDataProvider.FIELD_LOADED, 0);
            }
            equip.setLoaded(1);
            db.updateWithField(equip, EquipmentDataProvider.FIELD_LOADED, 1);
        }
    }

    /**
     * 获取用户装备列表
     * @return List<Equipment>
     */
    public List<Equipment> getAllEquipments(){
        List<Equipment> list = new ArrayList<>();
        for(Map.Entry<Long, Equipment> entry : equipments.entrySet()){
            if(entry.getValue().getIsDelete() == 0){
                list.add(entry.getValue());
            }
        }
        Collections.sort(list, equipmentComparator);
        return list;
    }


    /**
     * 获取用户上阵装备属性
     * @return AddtionCollection
     */
    public AddtionCollection getAdditionAttr(){
        AddtionCollection addtion = new AddtionCollection();
        for(Map.Entry<Long, Equipment> entry : equipments.entrySet()){
            if(entry.getValue().getIsDelete() == 0 && entry.getValue().getLoaded() > 0){
                AddtionCollection equipAddtion = getEquipAttr(entry.getValue());
                addtion.add(equipAddtion);
            }
        }
        return addtion;
    }


    /**
     * 计算装备加成属性
     * @param equip 要获取属性的装备对象
     * @return AddtionValue
     */
    private AddtionCollection getEquipAttr(Equipment equip){
        AddtionCollection collection = new AddtionCollection();
        if(equip != null && equip.getIsDelete() == 0 && equip.getLoaded() > 0){
            AddtionValue value = null;
            EquipmentTemplet et = equip.getTemplet();
            EquipmentQurlityTemplet qt = EquipmentQurlityTempletCfg.getEquipmentQurlityTempletById(equip.getQuality());

            //属性计算公式 : (初始属性加成 + 初始属性加成增量) + (属性加成成长 + 属性加成成长增量) * (装备等级 - 1)
            float[] args = null;
            switch (et.getType()){          //根据装备类型决定加成的属性类型
                case 1 :
                    value = new AddtionValue(AddtionType.ATTACK_ADDTION);
                    args = new float[]{et.getAttackAdd(), qt.getAttackAddInc(), et.getAttackAddUp(), qt.getAttackAddUpInc()};
                    break;
                case 2 :
                    value = new AddtionValue(AddtionType.SKILL_COLD_ADDTION);
                    args = new float[]{et.getSkillCooling(), qt.getSkillCoolingInc(), et.getSkillCoolingUp(), qt.getSkillCoolingUpInc()};
                    break;
                case 3 :
                    value = new AddtionValue(AddtionType.HP_ADDTION);
                    args = new float[]{et.getHp(), qt.getHpInc(), et.getHpUp(), qt.getHpUpInc()};
                    break;
            }

            if(args != null && value != null){
                float attr = (args[0] + args[1]) + (args[2] + args[3]) * (equip.getLevel() - 1);
                if(et.getType() == 3){      //目前只有HP是直接增加数值,  其他都是增加百分比
                    value.setAddtionNum(Math.round(attr));
                }else{
                    value.setAddtionPercent(attr);
                }
                collection.add(value);

                AddtionValue goldValue = new AddtionValue(AddtionType.CASH_ADDTION);
                AddtionValue scoreValue = new AddtionValue(AddtionType.SCORE_ADDTION);
                goldValue.setAddtionPercent(qt.getGold());
                scoreValue.setAddtionPercent(qt.getScore());

                collection.add(goldValue);
                collection.add(scoreValue);
            }else{
                throw new ClientException(ErrorCode.EQUIPMENT_TYPE_ERROR, "装备类型错误,模版ID:"+et.getId()+",类型:"+et.getType());
            }
        }
        return collection;
    }

    /**
     * 根据装备类型获取已穿戴上的装备
     * @param type 类型 1:主武器,2:副武器,3:护甲
     * @return Equipment
     */
    private Equipment getLoadedEquipByType(int type){
        Equipment equip = null;
        for(Map.Entry<Long, Equipment> entry : equipments.entrySet()){
            if(entry.getValue().getIsDelete() == 0){
                if(entry.getValue().getTemplet().getType() == type && entry.getValue().getLoaded() == 1){
                    equip = entry.getValue();
                    break;
                }
            }
        }
        return equip;
    }

    /**
     * 删除用户装备, 如果品质在太阳以上则只修改装备
     * @param equip 要删除的装备
     */
    private void deleteEquip(Equipment equip){
        equipments.remove(equip.getId());
        if(equip.getQuality() >= 9){
            db.updateWithField(equip, EquipmentDataProvider.FIELD_ISDELETE, 1);
        }else{
            db.remove((int)equip.getId());
        }
    }

    /**
     * 根据装备品质和装备模版ID获取用户拥有的装备数量
     * @param quality 指定品质
     * @param templetId 指定模版ID
     * @return int 拥有数量
     */
    private List<Equipment> getEquipCountByArgs(int quality, int templetId){
        List<Equipment> list = new ArrayList<>();
        for(Map.Entry<Long, Equipment> entry : equipments.entrySet()){
            Equipment equip = entry.getValue();

            if(equip.getQuality() == quality && equip.getTemplet().getId() == templetId && equip.getIsDelete() == 0){
                list.add(equip);
            }
        }
        return list;
    }

    /**
     * 检查装备是否存在
     * @param equip 装备对象
     * @param eid 装备id
     * @param method 调用方法
     * @param user 用户数据
     * @return true | false
     */
    private boolean checkEquipExsit(Equipment equip, long eid, String method, User user){
        String uname = user.getUserBaseInfo().getUserName();
        if(equip == null){
            throw new ClientException(ErrorCode.EQUIPMENT_NOT_FOUND, "["+method+"]装备不存在,Id:"+eid+",User:"+uname);
        }
        if(equip.getIsDelete() > 0){
            throw new ClientException(ErrorCode.EQUIPMENT_HAS_BEEN_DELETED, "["+method+"]装备已被删除,Id"+eid+",User:"+uname);
        }
        return true;
    }

}
