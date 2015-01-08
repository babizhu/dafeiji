package com.hz.dafeiji.cfg.wing;

import org.jdom2.Element;

/**
 * 模版
 *
 * @author liukun
 *         2015-1-8 15:13:57
 */
public class WingQurlityTemplet{

    /**
     * id
     */
    private final int id;


    /**
     * id
     */
    public int getId(){
        return id;
    }

    /**
     * 品阶id
     */
    private final int quality;


    /**
     * 品阶id
     */
    public int getQuality(){
        return quality;
    }

    /**
     * 品阶等级
     */
    private final int step;


    /**
     * 品阶等级
     */
    public int getStep(){
        return step;
    }

    /**
     * 实星数
     */
    private final int starCount;


    /**
     * 实星数
     */
    public int getStarCount(){
        return starCount;
    }

    /**
     * 所能升级的最大等级
     */
    private final int maxLv;


    /**
     * 所能升级的最大等级
     */
    public int getMaxLv(){
        return maxLv;
    }

    /**
     * 金币加成
     */
    private final float gold;


    /**
     * 金币加成
     */
    public float getGold(){
        return gold;
    }

    /**
     * 分数加成
     */
    private final float score;


    /**
     * 分数加成
     */
    public float getScore(){
        return score;
    }

    /**
     * 攻速增量
     */
    private final float aspdUpInc;


    /**
     * 攻速增量
     */
    public float getAspdUpInc(){
        return aspdUpInc;
    }

    /**
     * 攻击成长增量
     */
    private final float attackUpInc;


    /**
     * 攻击成长增量
     */
    public float getAttackUpInc(){
        return attackUpInc;
    }

    /**
     * 升级单级经验差
     */
    private final int expGap;


    /**
     * 升级单级经验差
     */
    public int getExpGap(){
        return expGap;
    }

    /**
     * 初始经验
     */
    private final int expInitial;


    /**
     * 初始经验
     */
    public int getExpInitial(){
        return expInitial;
    }

    /**
     * 进阶消耗进阶卡数量
     */
    private final int advanceCard;


    /**
     * 进阶消耗进阶卡数量
     */
    public int getAdvanceCard(){
        return advanceCard;
    }

    /**
     * 进阶消耗5品质僚机数量
     */
    private final int advanceWing;


    /**
     * 进阶消耗5品质僚机数量
     */
    public int getAdvanceWing(){
        return advanceWing;
    }

    /**
     * 进阶消耗金币
     */
    private final int advanceGold;


    /**
     * 进阶消耗金币
     */
    public int getAdvanceGold(){
        return advanceGold;
    }

    /**
     * 进阶消耗钻石
     */
    private final int advanceJewel;


    /**
     * 进阶消耗钻石
     */
    public int getAdvanceJewel(){
        return advanceJewel;
    }

    /**
     * 说明
     */
    private final String state;


    /**
     * 说明
     */
    public String getState(){
        return state;
    }

    public WingQurlityTemplet( Element element ){
        id = Integer.parseInt( element.getChildText( "id" ).trim() );
        quality = Integer.parseInt( element.getChildText( "quality" ).trim() );
        step = Integer.parseInt( element.getChildText( "step" ).trim() );
        starCount = Integer.parseInt( element.getChildText( "starCount" ).trim() );
        maxLv = Integer.parseInt( element.getChildText( "maxLv" ).trim() );
        gold = Float.parseFloat( element.getChildText( "gold" ).trim() );
        score = Float.parseFloat( element.getChildText( "score" ).trim() );
        aspdUpInc = Float.parseFloat( element.getChildText( "aspdUpInc" ).trim() );
        attackUpInc = Float.parseFloat( element.getChildText( "attackUpInc" ).trim() );
        expGap = Integer.parseInt( element.getChildText( "expGap" ).trim() );
        expInitial = Integer.parseInt( element.getChildText( "expInitial" ).trim() );
        advanceCard = Integer.parseInt( element.getChildText( "advanceCard" ).trim() );
        advanceWing = Integer.parseInt( element.getChildText( "advanceWing" ).trim() );
        advanceGold = Integer.parseInt( element.getChildText( "advanceGold" ).trim() );
        advanceJewel = Integer.parseInt( element.getChildText( "advanceJewel" ).trim() );
        state = element.getChildText( "state" ).trim();

    }

    @Override
    public String toString(){
        return "WingQurlityTemplet [id = " + id + ",quality = " + quality + ",step = " + step + ",starCount = " + starCount + ",maxLv = " + maxLv + ",gold = " + gold + ",score = " + score + ",aspdUpInc = " + aspdUpInc + ",attackUpInc = " + attackUpInc + ",expGap = " + expGap + ",expInitial = " + expInitial + ",advanceCard = " + advanceCard + ",advanceWing = " + advanceWing + ",advanceGold = " + advanceGold + ",advanceJewel = " + advanceJewel + ",state = " + state + "]";
    }

	/*自定义代码开始*//*自定义代码结束*/
}
