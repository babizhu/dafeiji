package com.hz.dafeiji.cfg.equipment;

import org.jdom2.Element;

/**
 * 模版
 *
 * @author liukun
 *         2015-1-4 13:44:25
 */
public class EquipmentQurlityTemplet{

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
     * 初始攻击加成增量
     */
    private final float attackAddInc;


    /**
     * 初始攻击加成增量
     */
    public float getAttackAddInc(){
        return attackAddInc;
    }

    /**
     * 初始技能冷却增量
     */
    private final float skillCoolingInc;


    /**
     * 初始技能冷却增量
     */
    public float getSkillCoolingInc(){
        return skillCoolingInc;
    }

    /**
     * 初始生命值增量
     */
    private final float hpInc;


    /**
     * 初始生命值增量
     */
    public float getHpInc(){
        return hpInc;
    }

    /**
     * 攻击加成成长增量
     */
    private final float attackAddUpInc;


    /**
     * 攻击加成成长增量
     */
    public float getAttackAddUpInc(){
        return attackAddUpInc;
    }

    /**
     * 技能冷却成长增量
     */
    private final float skillCoolingUpInc;


    /**
     * 技能冷却成长增量
     */
    public float getSkillCoolingUpInc(){
        return skillCoolingUpInc;
    }

    /**
     * 生命成长增量
     */
    private final float hpUpInc;


    /**
     * 生命成长增量
     */
    public float getHpUpInc(){
        return hpUpInc;
    }

    /**
     * 进阶消耗图纸数量
     */
    private final int advanceDraw;


    /**
     * 进阶消耗图纸数量
     */
    public int getAdvanceDraw(){
        return advanceDraw;
    }

    /**
     * 进阶消耗5品质同件装备数量
     */
    private final int advanceEquipment;


    /**
     * 进阶消耗5品质同件装备数量
     */
    public int getAdvanceEquipment(){
        return advanceEquipment;
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

    public EquipmentQurlityTemplet( Element element ){
        id = Integer.parseInt( element.getChildText( "id" ).trim() );
        quality = Integer.parseInt( element.getChildText( "quality" ).trim() );
        step = Integer.parseInt( element.getChildText( "step" ).trim() );
        starCount = Integer.parseInt( element.getChildText( "starCount" ).trim() );
        maxLv = Integer.parseInt( element.getChildText( "maxLv" ).trim() );
        gold = Float.parseFloat( element.getChildText( "gold" ).trim() );
        score = Float.parseFloat( element.getChildText( "score" ).trim() );
        attackAddInc = Float.parseFloat( element.getChildText( "attackAddInc" ).trim() );
        skillCoolingInc = Float.parseFloat( element.getChildText( "skillCoolingInc" ).trim() );
        hpInc = Float.parseFloat( element.getChildText( "hpInc" ).trim() );
        attackAddUpInc = Float.parseFloat( element.getChildText( "attackAddUpInc" ).trim() );
        skillCoolingUpInc = Float.parseFloat( element.getChildText( "skillCoolingUpInc" ).trim() );
        hpUpInc = Float.parseFloat( element.getChildText( "hpUpInc" ).trim() );
        advanceDraw = Integer.parseInt( element.getChildText( "advanceDraw" ).trim() );
        advanceEquipment = Integer.parseInt( element.getChildText( "advanceEquipment" ).trim() );
        advanceGold = Integer.parseInt( element.getChildText( "advanceGold" ).trim() );
        advanceJewel = Integer.parseInt( element.getChildText( "advanceJewel" ).trim() );
        state = element.getChildText( "state" ).trim();

    }

    @Override
    public String toString(){
        return "EquipmentQurlityTemplet [id = " + id + ",quality = " + quality + ",step = " + step + ",starCount = " + starCount + ",maxLv = " + maxLv + ",gold = " + gold + ",score = " + score + ",attackAddInc = " + attackAddInc + ",skillCoolingInc = " + skillCoolingInc + ",hpInc = " + hpInc + ",attackAddUpInc = " + attackAddUpInc + ",skillCoolingUpInc = " + skillCoolingUpInc + ",hpUpInc = " + hpUpInc + ",advanceDraw = " + advanceDraw + ",advanceEquipment = " + advanceEquipment + ",advanceGold = " + advanceGold + ",advanceJewel = " + advanceJewel + ",state = " + state + "]";
    }

	/*自定义代码开始*//*自定义代码结束*/
}
