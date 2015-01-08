package com.hz.dafeiji.ai.user.modules.equipments;

import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.cfg.equipment.EquipmentQurlityTemplet;
import com.hz.dafeiji.cfg.equipment.EquipmentQurlityTempletCfg;
import com.hz.dafeiji.cfg.equipment.EquipmentTempletCfg;
import com.hz.dafeiji.cfg.manual.EquipExpCfg;
import lombok.Data;
import lombok.ToString;
import java.util.*;

/**
 * user         valen
 * time         14-3-25 下午1:52
 * 装备模块
 */

@Data
@ToString
public class EquipmentModule{

    final EquipmentDataProvider db;

    private static Comparator<Equipment> equipmentComparator = null;


    /**
     * 所有的道具
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
     * @param equipIds 要添加的道具模版ID列表, 逗号分割
     */
    public void add(String equipIds){
        for(String id : equipIds.split(",")){
            if(!id.equals("")){
                int equipTempleteId = Integer.valueOf(id);
                if(EquipmentTempletCfg.getEquipmentTempletById(equipTempleteId) != null){
                    Equipment equip = new Equipment(equipTempleteId);

                    equipments.put(equip.getId(), equip);
                    db.add(equip);
                }
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
                Equipment equip = getEquipmentById(Integer.valueOf(id));
                if(equip != null){
                    if(equip.getIsDelete() == 0){
                        int energy = EquipExpCfg.getSplitExp(equip.getQuality(), equip.getLevel(), equip.getTemplet().getType());  //分解装备获取的能源

                        //删除该装备, 品质太阳级的装备只修改状态，不删数据
                        equipments.remove(equip.getId());
                        if(equip.getQuality() >= 9){
                            db.updateWithField(equip, EquipmentDataProvider.FIELD_ISDELETE, 1);
                        }else{
                            db.remove((int)equip.getId());
                        }
                        user.getModuleManager().getPropertyModule().changeEnergy(energy);       //添加用户能源数据
                    }
                }
            }
        }
    }

    /**
     * 装备升级
     * @param equipId 要升级的装备ID
     * @param user USER对象用于扣除相关消耗品
     */
    public void EquipLevelUp(long equipId, User user){
        Equipment equip = equipments.get(equipId);
        if(equip != null){
            EquipmentQurlityTemplet et = EquipmentQurlityTempletCfg.getEquipmentQurlityTempletById(equip.getQuality());
            if(et != null){

            }

            int energyCost = EquipExpCfg.getExp(equip.getQuality(), equip.getLevel(), equip.getTemplet().getType());

        }
    }

    /**
     * 装备进阶
     * @param equipId 要进阶的装备ID
     * @param user USER对象用于扣除相关消耗品
     */
    public void EquipUpgrade(long equipId, User user){
        Equipment equip = equipments.get(equipId);
        if(equip != null){

        }
    }



    /**
     * 根据装备ID获取用户装备
      * @param equipmentId 装备数据库ID
     * @return Equipment
     */
    public Equipment getEquipmentById(long equipmentId){
        return equipments.get( equipmentId );
    }

    /**
     * 根据装备类型获取已穿戴上的装备
     * @param type 类型 1:主武器,2:副武器,3:护甲
     * @return Equipment
     */
    public Equipment getLoadedEquipByType(int type){
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
     * 穿戴装备
     * @param equip 要穿戴的装备
     */
    public void loadEquip(Equipment equip){
        if(equipments.get(equip.getId()) != null){
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
     * @return
     */
    public List<Equipment> getEquipment(){
        List<Equipment> list = new ArrayList<>();
        for(Map.Entry<Long, Equipment> entry : equipments.entrySet()){
            if(entry.getValue().getIsDelete() == 0){
                list.add(entry.getValue());
            }
        }
        Collections.sort(list, equipmentComparator);
        return list;
    }


}
