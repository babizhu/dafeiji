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
    private final WingQurlityTemplet wqTemplet;
    private int exp;


    public Wing( Long id, WingTemplet templet ){
        this.id = id;
        this.templet = templet;
        wqTemplet = WingQurlityTempletCfg.getWingQurlityTempletById( templet.getQuality() );
    }

    public int getAttack(){
        return (int) (templet.getAttack() + (level - 1) * (templet.getAttackUp() + wqTemplet.getAttackUpInc()));
    }

    public int getAttackSpeed(){
        return (int) (templet.getAspd() + wqTemplet.getAspdUpInc());
    }
}
