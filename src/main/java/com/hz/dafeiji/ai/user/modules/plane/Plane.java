package com.hz.dafeiji.ai.user.modules.plane;

import com.bbz.tool.db.IdentityObj;
import com.hz.dafeiji.cfg.plane.PlaneTemplet;
import lombok.Data;

/**
 * user         LIUKUN
 * time         2014-12-25 17:21
 * 飞机也就是机甲
 */

@Data
public class Plane implements IdentityObj{

    private long id;
    //private int hp;
    //private int attack;
    private int level = 1;
    private PlaneTemplet templet;
    /**
     * 是否当前的出战飞机
     */
    private boolean isCurrent;


    public Plane( Long id, PlaneTemplet templet ){
        this.id = id;
        this.templet = templet;
    }

    public int getAttack(){
        return (int) (templet.getAttack() + (level - 1) * templet.getAttackUp());
    }

    public int getHp(){
        return (int) (templet.getHp() + (level - 1) * templet.getHpUp());
    }
}
