package com.hz.dafeiji.ai.user.modules.wing;

import com.bbz.tool.db.IdentityObj;
import com.hz.dafeiji.cfg.wing.WingQurlityTemplet;
import com.hz.dafeiji.cfg.wing.WingQurlityTempletCfg;
import com.hz.dafeiji.cfg.wing.WingTemplet;
import lombok.Data;

/**
 * user         LIUKUN
 * time         2015-1-7 17:00
 */

@Data
public class Wing implements IdentityObj{
    private long id;
    private int level = 1;
    private final WingTemplet templet;
    //private WingQurlityTemplet wqTemplet;
    private int exp;
    private int quality;
    private boolean lock;

    /**
     * 是否当前的出战飞机
     */
    private boolean isCurrent;



    public Wing( Long id, WingTemplet templet ){
        this.id = id;
        this.templet = templet;

    }

    public int getAttack(){
        return (int) (templet.getAttack() + (level - 1) * (templet.getAttackUp() + getWqTemplet().getAttackUpInc()));
    }

    public int getAttackSpeed(){
        return (int) (templet.getAspd() + getWqTemplet().getAspdUpInc());
    }

    public WingQurlityTemplet getWqTemplet(){
        return WingQurlityTempletCfg.getWingQurlityTempletById( quality );
    }
}
