package com.hz.dafeiji.ai.user.modules.plane;

import com.bbz.tool.db.IdentityObj;
import com.hz.dafeiji.ai.addtion.AddtionType;
import com.hz.dafeiji.ai.addtion.AddtionValue;
import com.hz.dafeiji.cfg.plane.PlaneTemplet;
import lombok.Data;

import java.util.EnumMap;

/**
 * user         LIUKUN
 * time         2014-12-25 17:21
 * 飞机也就是机甲
 */

@Data
public class Plane implements IdentityObj{

    private long id;

    private int level = 1;
    private PlaneTemplet templet;

    private final int hpBase, attackBase;

    private int hp;
    private int attack;
    private int cashAddtion;
    private int scoreAddtion;
    private float skillColdAddtion;

    /**
     * 是否当前的出战飞机
     */
    private boolean isCurrent;


    public Plane( Long id, PlaneTemplet templet ){
        this.id = id;
        this.templet = templet;

        hpBase = (int) (templet.getHp() + (level - 1) * templet.getHpUp());
        attackBase = (int) (templet.getAttack() + (level - 1) * templet.getAttackUp());
    }

    public void calcAddtion( EnumMap<AddtionType, AddtionValue> addtionMap ){

        for( AddtionValue addtionValue : addtionMap.values() ) {
            switch( addtionValue.getAddtionItem() ) {
                case HP_ADDTION:
                    if( addtionValue.getAddtionNum() != 0 ) {
                        hp += hpBase + addtionValue.getAddtionNum();

                    } else{
                        hp += ((int) (hpBase * (1 + addtionValue.getAddtionPercent())));
                    }
                    break;
                case CASH_ADDTION:
                    if( addtionValue.getAddtionNum() != 0 ) {
                        cashAddtion += addtionValue.getAddtionNum();
                    } else{
                        cashAddtion += addtionValue.getAddtionNum();
                    }
                    break;
                case ATTACK_ADDTION:
                    if( addtionValue.getAddtionNum() != 0 ) {
                        attack += attackBase + addtionValue.getAddtionNum();
                    } else{
                        attack += (attackBase * (1 + addtionValue.getAddtionPercent()));
                    }
                    break;
                case SCORE_ADDTION:
                    if( addtionValue.getAddtionNum() != 0 ) {
                        scoreAddtion += addtionValue.getAddtionNum();
                    } else{
                        scoreAddtion += addtionValue.getAddtionNum();
                    }
                    break;
                case SKILL_COLD_ADDTION:
                    if( addtionValue.getAddtionNum() != 0 ) {
                        skillColdAddtion += addtionValue.getAddtionNum();
                    } else{
                        skillColdAddtion += addtionValue.getAddtionNum();
                    }
                    break;
                default:
                    throw new RuntimeException( "新增了不认识的属性加成？ " + addtionValue.getAddtionItem() );
            }
        }
    }
}
