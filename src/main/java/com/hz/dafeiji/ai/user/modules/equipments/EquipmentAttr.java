package com.hz.dafeiji.ai.user.modules.equipments;

import com.hz.dafeiji.cfg.equipment.EquipmentQurlityTemplet;
import com.hz.dafeiji.cfg.equipment.EquipmentQurlityTempletCfg;
import com.hz.dafeiji.cfg.equipment.EquipmentTemplet;

/**
 * Created by Valen on 2015/1/9.
 *
 */
public class EquipmentAttr {
    //(初始属性加成 + 初始属性加成增量) + (属性加成成长 + 属性加成成长增量) * (装备等级 - 1)

    /**
     * 初始属性加成
     */
    private static float BASE_ATTR;

    /**
     * 初始属性加成增量
     */
    private static float BASE_ATTR_RATE;

    /**
     * 属性加成成长
     */
    private static float ATTR_GROW;

    /**
     * 属性加成成长增量
     */
    private static float ATTR_GROW_RATE;

    /**
     * 根据装备模版和品质决定公式属性值
     */
    public EquipmentAttr(EquipmentTemplet templet, int quality){
        switch (templet.getType()){
            case 1 :
                this.BASE_ATTR = templet.getAttackAdd();
                this.ATTR_GROW = templet.getAttackAddUp();
                break;
            case 2 :
                this.BASE_ATTR = templet.getSkillCooling();
                this.ATTR_GROW = templet.getSkillCoolingUp();
                break;
            case 3 :
                this.BASE_ATTR = templet.getHp();
                this.ATTR_GROW = templet.getHpUp();
                break;
        }
        setAttrGrowRate(templet, quality);
    }

    /**
     * 根据品质设置属性
     */
    public void setAttrGrowRate(EquipmentTemplet templet, int quality){
        EquipmentQurlityTemplet wt = EquipmentQurlityTempletCfg.getEquipmentQurlityTempletById(quality);
        if(wt != null){
            switch (templet.getType()){
                case 1 :
                    this.BASE_ATTR_RATE = wt.getAttackAddInc();
                    this.ATTR_GROW_RATE = wt.getAttackAddUpInc();
                    break;
                case 2 :
                    this.BASE_ATTR_RATE = wt.getSkillCoolingInc();
                    this.ATTR_GROW_RATE = wt.getSkillCoolingUpInc();
                    break;
                case 3 :
                    this.BASE_ATTR_RATE = wt.getHpInc();
                    this.ATTR_GROW_RATE = wt.getHpUpInc();
                    break;
            }
        }
    }

    /**
     * 获取属性加成值
     * @return 加成百分比,保留千分位, 比如加成0.112  返回的是1.112，计算的时候直接乘以返回值再取整
     */
    public float getAttrIncrease(){
        return 0f;
    }
}
