package com.hz.dafeiji.ai.user.modules.fighters;


import lombok.Data;

/**
 * user         LIUKUN
 * time         14-3-25 下午1:34
 */

@Data
public class BaseFighter{
    // protected final FighterTemplet templet;
    private int hp;
    private int hpMax;
    private int sp;
    private int spMax;
    private int phyAttack;
    private int phyDefender;
    private int magicAttack;
    private int magicDefender;
}
