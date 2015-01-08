package com.hz.dafeiji.cfg.equipment;

import org.jdom2.Element;

/**
 * 模版
 *
 * @author liukun
 *         2015-1-8 10:02:34
 */
public class EquipmentTemplet{

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
     * 装备名称
     */
    private final String name;


    /**
     * 装备名称
     */
    public String getName(){
        return name;
    }

    /**
     * 类型（1主武器2副武器3护甲）
     */
    private final int type;


    /**
     * 类型（1主武器2副武器3护甲）
     */
    public int getType(){
        return type;
    }

    /**
     * 当前品阶
     */
    private final int quality;


    /**
     * 当前品阶
     */
    public int getQuality(){
        return quality;
    }

    /**
     * 最高品阶
     */
    private final int qualityMax;


    /**
     * 最高品阶
     */
    public int getQualityMax(){
        return qualityMax;
    }

    /**
     * 当前等级
     */
    private final int currentLv;


    /**
     * 当前等级
     */
    public int getCurrentLv(){
        return currentLv;
    }

    /**
     * 初始攻击加成
     */
    private final float attackAdd;


    /**
     * 初始攻击加成
     */
    public float getAttackAdd(){
        return attackAdd;
    }

    /**
     * 初始技能冷却
     */
    private final float skillCooling;


    /**
     * 初始技能冷却
     */
    public float getSkillCooling(){
        return skillCooling;
    }

    /**
     * 初始生命值
     */
    private final int hp;


    /**
     * 初始生命值
     */
    public int getHp(){
        return hp;
    }

    /**
     * 攻击加成成长
     */
    private final float attackAddUp;


    /**
     * 攻击加成成长
     */
    public float getAttackAddUp(){
        return attackAddUp;
    }

    /**
     * 技能冷却成长
     */
    private final float skillCoolingUp;


    /**
     * 技能冷却成长
     */
    public float getSkillCoolingUp(){
        return skillCoolingUp;
    }

    /**
     * 生命成长
     */
    private final float hpUp;


    /**
     * 生命成长
     */
    public float getHpUp(){
        return hpUp;
    }

    /**
     * 技能id
     */
    private final int skill;


    /**
     * 技能id
     */
    public int getSkill(){
        return skill;
    }

    /**
     * 装备描述
     */
    private final String describe;


    /**
     * 装备描述
     */
    public String getDescribe(){
        return describe;
    }

    public EquipmentTemplet( Element element ){
        id = Integer.parseInt( element.getChildText( "id" ).trim() );
        name = element.getChildText( "name" ).trim();
        type = Integer.parseInt( element.getChildText( "type" ).trim() );
        quality = Integer.parseInt( element.getChildText( "quality" ).trim() );
        qualityMax = Integer.parseInt( element.getChildText( "qualityMax" ).trim() );
        currentLv = Integer.parseInt( element.getChildText( "currentLv" ).trim() );
        attackAdd = Float.parseFloat( element.getChildText( "attackAdd" ).trim() );
        skillCooling = Float.parseFloat( element.getChildText( "skillCooling" ).trim() );
        hp = Integer.parseInt( element.getChildText( "hp" ).trim() );
        attackAddUp = Float.parseFloat( element.getChildText( "attackAddUp" ).trim() );
        skillCoolingUp = Float.parseFloat( element.getChildText( "skillCoolingUp" ).trim() );
        hpUp = Float.parseFloat( element.getChildText( "hpUp" ).trim() );
        skill = Integer.parseInt( element.getChildText( "skill" ).trim() );
        describe = element.getChildText( "describe" ).trim();

    }

    @Override
    public String toString(){
        return "EquipmentTemplet [id = " + id + ",name = " + name + ",type = " + type + ",quality = " + quality + ",qualityMax = " + qualityMax + ",currentLv = " + currentLv + ",attackAdd = " + attackAdd + ",skillCooling = " + skillCooling + ",hp = " + hp + ",attackAddUp = " + attackAddUp + ",skillCoolingUp = " + skillCoolingUp + ",hpUp = " + hpUp + ",skill = " + skill + ",describe = " + describe + "]";
    }

	/*自定义代码开始*//*自定义代码结束*/
}
