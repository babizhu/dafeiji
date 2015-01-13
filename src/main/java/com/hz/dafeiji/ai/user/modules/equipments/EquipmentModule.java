package com.hz.dafeiji.ai.user.modules.equipments;

import com.bbz.tool.common.Transform;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.addtion.AddtionCollection;
import com.hz.dafeiji.ai.addtion.AddtionType;
import com.hz.dafeiji.ai.addtion.AddtionValue;
import com.hz.dafeiji.ai.user.modules.ModuleManager;
import com.hz.dafeiji.ai.user.modules.award.AwardModule;
import com.hz.dafeiji.cfg.define.PropIdDefine;
import com.hz.dafeiji.cfg.equipment.EquipmentQurlityTemplet;
import com.hz.dafeiji.cfg.equipment.EquipmentQurlityTempletCfg;
import com.hz.dafeiji.cfg.equipment.EquipmentTemplet;
import com.hz.dafeiji.cfg.equipment.EquipmentTempletCfg;
import com.hz.dafeiji.cfg.manual.EquipExpCfg;
import com.mongodb.BasicDBObject;

import java.util.*;

/**
 * user         valen
 * time         14-3-25 下午1:52
 * 装备模块
 */

public class EquipmentModule{

    final EquipmentDataProvider db;

    private static Comparator<Equipment> equipmentComparator = null;
    private String className;
    private final AwardModule awardModule;

    static {
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
     * 背包里的装备
     */
    private Map<Long, Equipment> equipments;

    public EquipmentModule(ModuleManager moduleManager){
        db = new EquipmentDataProvider(moduleManager.getUserName());
        awardModule = moduleManager.getAwardModule();
        className = getClass().getSimpleName();

        equipments = db.getMapAllBy(new BasicDBObject(EquipmentDataProvider.FIELD_ISDELETE,0)); //查询isDelete字段为0的数据
    }

    /**
     * 添加一件新装备
     * @param templetIds 要添加的道具模版ID列表, 逗号分割
     */
    public void addAll(String templetIds){
        int[] templeArr = Transform.ArrayType.toInts(templetIds);
        for (int templetId : templeArr) {
            if(EquipmentTempletCfg.getEquipmentTempletById(templetId) != null){
                Equipment equip = new Equipment(templetId);
                equipments.put(equip.getId(), equip);
                db.add(equip);
            }else{
                throw new ClientException(ErrorCode.EQUIPMENT_TEMPLET_NOT_FOUND, "要添加的装备模版ID不存在,传递的模版ID:"+templetId);
            }
        }
    }

    /**
     * 分解装备
     * @param equipIds 要分解的装备id列表,逗号分割
     */
    public void splitEquip(String equipIds){
        long[] equipIdArr = Transform.ArrayType.toLongs(equipIds);
        int totalEnergy = 0;
        for(long equipId : equipIdArr){
            Equipment equip = getEquipmentById(equipId);
            totalEnergy += EquipExpCfg.getSplitExp(equip.getQuality(), equip.getLevel(), equip.getTemplet().getType());  //分解装备获取的能源
            removeEquip(equip);         //删除装备
        }
        awardModule.addAward(PropIdDefine.NENG_YUAN + "," + totalEnergy, className + ".splitEquip");
    }

    /**
     * 装备升级
     * @param equipId 要升级的装备ID
     */
    public void levelUpEquip(long equipId){
        Equipment equip = getEquipmentById(equipId);
        EquipmentQurlityTemplet et = EquipmentQurlityTempletCfg.getEquipmentQurlityTempletById(equip.getQuality());

        if(equip.getLevel() >= et.getMaxLv()){
            throw new ClientException(ErrorCode.EQUIPMENT_LEVEL_OVER_LIMIT,"装备升级超出等级限制,品质:"+equip.getQuality()
                    +",当前等级:"+equip.getLevel()
                    +",上限:"+et.getMaxLv());
        }

        //计算消耗能源
        int energyCost = EquipExpCfg.getExp(equip.getQuality(), equip.getLevel(), equip.getTemplet().getType());

        //扣除能源
        awardModule.reduceAward(PropIdDefine.NENG_YUAN + "," + energyCost, className + ".levelUpEquip");

        //装备升级
        equip.levelUp();
        db.updateWithField(equip, EquipmentDataProvider.FIELD_LEVEL, equip.getLevel());
    }

    /**
     * 装备进阶
     * @param equipId 要进阶的装备ID
     */
    public void upgradeEquip(long equipId){
        String methodName = className + ".upgradeEquip";
        Equipment equip = getEquipmentById(equipId);
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

        List<Equipment> costEquips = new ArrayList<>();
        if(et.getAdvanceEquipment() > 0){       //如果需要消耗装备
            for(Map.Entry<Long, Equipment> entry : equipments.entrySet()){
                Equipment temp = entry.getValue();
                if(temp.getIsDelete() == 0){
                    if(temp.getQuality() == et.getAdvanceEquipmentQue() &&              //待吞噬的装备品质 = 该装备需要的材料装备品质
                       temp.getTemplet().getId() == equip.getTemplet().getId() &&       //待吞噬的装备模版ID = 该装备需要的材料装备模版ID
                       temp.getId() != equipId &&                                       //待吞噬的装备不能是要升级的装备
                       temp.getLoaded() == 0){                                          //待吞噬的装备不能是装备中的

                        costEquips.add(temp);
                    }
                }
            }
            //消耗装备数量符合
            if(costEquips.size() < et.getAdvanceEquipment()){
                throw new ClientException(ErrorCode.EQUIPMENT_UPGRADE_EQUIP_NOT_ENOUGH
                        , "进阶需要的装备不够,需要品质"+et.getAdvanceEquipmentQue()+"的装备"+et.getAdvanceEquipment()
                        +"件,拥有:"+costEquips.size()+"件");
            }
        }
        String costProps = PropIdDefine.CASH_JIN_BI + "," + et.getAdvanceGold() + ","
                         + PropIdDefine.CASH_ZUAN_SHI + "," + et.getAdvanceJewel();

        awardModule.reduceAward(costProps, methodName);     //扣除需要的Prop
        for(Equipment e : costEquips){
            removeEquip(e);     //删除被吃掉的装备
        }

        equip.upgradeQuality();
        db.updateWithField(equip, EquipmentDataProvider.FIELD_QUALITY, equip.getQuality());
    }


    /**
     * 穿戴装备
     * @param equipId 要穿戴的装备Id
     */
    public void loadEquip(long equipId){
        Equipment equip = getEquipmentById(equipId);
        Equipment replaceEquip = getLoadedEquipByType(equip.getTemplet().getType());

        if(replaceEquip != null){      //该类型已经有装备穿戴上, 替换成传入的装备
            replaceEquip.setLoaded(0);
            db.updateWithField(replaceEquip, EquipmentDataProvider.FIELD_LOADED, 0);
        }
        equip.setLoaded(1);
        db.updateWithField(equip, EquipmentDataProvider.FIELD_LOADED, 1);
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
    public AddtionCollection getAddtionCollection(){
        AddtionCollection addtion = new AddtionCollection();
        for(Map.Entry<Long, Equipment> entry : equipments.entrySet()){
            if(entry.getValue().getIsDelete() == 0 && entry.getValue().getLoaded() > 0){
                AddtionCollection equipAddtion = getEquipAddtion(entry.getValue());
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
    public AddtionCollection getEquipAddtion(Equipment equip){
        AddtionCollection collection = new AddtionCollection();
        EquipmentTemplet et = equip.getTemplet();
        EquipmentQurlityTemplet qt = EquipmentQurlityTempletCfg.getEquipmentQurlityTempletById(equip.getQuality());

        //属性计算公式 : (初始属性加成 + 初始属性加成增量) + (属性加成成长 + 属性加成成长增量) * (装备等级 - 1)
        AddtionValue value = null;
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

        if(value == null){
            throw new ClientException(ErrorCode.EQUIPMENT_TYPE_ERROR, "装备类型错误,模版ID:"+et.getId()+",类型:"+et.getType());
        }

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
        return collection;
    }

    /**
     * 通过装备唯一ID获取装备实例
     * @param id 唯一ID
     * @return Equipment
     */
    private Equipment getEquipmentById(long id){
        if(equipments.get(id) == null){
            throw new ClientException(ErrorCode.EQUIPMENT_NOT_FOUND, "未找到装备,装备id:"+id);
        }
        return equipments.get(id);
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
                if(entry.getValue().getTemplet().getType() == type && entry.getValue().getLoaded() > 0){
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
    private void removeEquip(Equipment equip){
        equipments.remove(equip.getId());
        if(equip.getQuality() >= 9){
            db.updateWithField(equip, EquipmentDataProvider.FIELD_ISDELETE, 1);
        }else{
            db.remove(equip);
        }
    }
}
