package com.hz.dafeiji.cfg.wing;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2015-1-13 10:01:06
 */
public class WingTemplet {

    /**
	 * id
	 */
    private final int id;



	/**
	 * id
	 */
	public int getId() {
		return id;
	}/**
	 * 名称
	 */
    private final String name;



	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}/**
	 * 品阶
	 */
    private final int quality;



	/**
	 * 品阶
	 */
	public int getQuality() {
		return quality;
	}/**
	 * 可进化到品阶
	 */
    private final int qualityMax;



	/**
	 * 可进化到品阶
	 */
	public int getQualityMax() {
		return qualityMax;
	}/**
	 * 当前等级
	 */
    private final int currentLv;



	/**
	 * 当前等级
	 */
	public int getCurrentLv() {
		return currentLv;
	}/**
	 * 五行属性（0无，1水，2火，3土，4风，5雷）
	 */
    private final int attribute;



	/**
	 * 五行属性（0无，1水，2火，3土，4风，5雷）
	 */
	public int getAttribute() {
		return attribute;
	}/**
	 * 初始攻击
	 */
    private final int attack;



	/**
	 * 初始攻击
	 */
	public int getAttack() {
		return attack;
	}/**
	 * 攻速（50）
	 */
    private final int aspd;



	/**
	 * 攻速（50）
	 */
	public int getAspd() {
		return aspd;
	}/**
	 * 攻击成长（7.5-8.4）
	 */
    private final float attackUp;



	/**
	 * 攻击成长（7.5-8.4）
	 */
	public float getAttackUp() {
		return attackUp;
	}/**
	 * 合成所需碎片数量
	 */
    private final int compound;



	/**
	 * 合成所需碎片数量
	 */
	public int getCompound() {
		return compound;
	}/**
	 * 可用万能碎片数量
	 */
    private final int allPurpose;



	/**
	 * 可用万能碎片数量
	 */
	public int getAllPurpose() {
		return allPurpose;
	}/**
	 * 基础卖价
	 */
    private final int cashBase;



	/**
	 * 基础卖价
	 */
	public int getCashBase() {
		return cashBase;
	}/**
	 * 资源id
	 */
    private final String resId;



	/**
	 * 资源id
	 */
	public String getResId() {
		return resId;
	}/**
	 * 弹幕id组
	 */
    private final int barrage;



	/**
	 * 弹幕id组
	 */
	public int getBarrage() {
		return barrage;
	}/**
	 * 技能id1
	 */
    private final int skill1;



	/**
	 * 技能id1
	 */
	public int getSkill1() {
		return skill1;
	}/**
	 * 技能id2
	 */
    private final int skill2;



	/**
	 * 技能id2
	 */
	public int getSkill2() {
		return skill2;
	}/**
	 * 僚机来源
	 */
    private final String source;



	/**
	 * 僚机来源
	 */
	public String getSource() {
		return source;
	}/**
	 * 僚机描述
	 */
    private final String describe;



	/**
	 * 僚机描述
	 */
	public String getDescribe() {
		return describe;
	}

	public WingTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
name = element.getChildText("name").trim();
quality = Integer.parseInt( element.getChildText("quality").trim() );
qualityMax = Integer.parseInt( element.getChildText("qualityMax").trim() );
currentLv = Integer.parseInt( element.getChildText("currentLv").trim() );
attribute = Integer.parseInt( element.getChildText("attribute").trim() );
attack = Integer.parseInt( element.getChildText("attack").trim() );
aspd = Integer.parseInt( element.getChildText("aspd").trim() );
attackUp = Float.parseFloat( element.getChildText("attackUp").trim() );
compound = Integer.parseInt( element.getChildText("compound").trim() );
allPurpose = Integer.parseInt( element.getChildText("allPurpose").trim() );
cashBase = Integer.parseInt( element.getChildText("cashBase").trim() );
resId = element.getChildText("resId").trim();
barrage = Integer.parseInt( element.getChildText("barrage").trim() );
skill1 = Integer.parseInt( element.getChildText("skill1").trim() );
skill2 = Integer.parseInt( element.getChildText("skill2").trim() );
source = element.getChildText("source").trim();
describe = element.getChildText("describe").trim();

	}

	@Override
	public String toString() {
		return "WingTemplet [id = " + id + ",name = " + name + ",quality = " + quality + ",qualityMax = " + qualityMax + ",currentLv = " + currentLv + ",attribute = " + attribute + ",attack = " + attack + ",aspd = " + aspd + ",attackUp = " + attackUp + ",compound = " + compound + ",allPurpose = " + allPurpose + ",cashBase = " + cashBase + ",resId = " + resId + ",barrage = " + barrage + ",skill1 = " + skill1 + ",skill2 = " + skill2 + ",source = " + source + ",describe = " + describe + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
