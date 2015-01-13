package com.hz.dafeiji.cfg.customs;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2015-1-13 14:35:25
 */
public class MonsterTemplet {

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
	 * 当前等级
	 */
    private final int currentLv;



	/**
	 * 当前等级
	 */
	public int getCurrentLv() {
		return currentLv;
	}/**
	 * 生命
	 */
    private final int hp;



	/**
	 * 生命
	 */
	public int getHp() {
		return hp;
	}/**
	 * 子弹攻击
	 */
    private final int attack;



	/**
	 * 子弹攻击
	 */
	public int getAttack() {
		return attack;
	}/**
	 * 撞击伤害
	 */
    private final int strike;



	/**
	 * 撞击伤害
	 */
	public int getStrike() {
		return strike;
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
	 * 移动速度
	 */
    private final int speed;



	/**
	 * 移动速度
	 */
	public int getSpeed() {
		return speed;
	}/**
	 * 怪物资源id
	 */
    private final String resId;



	/**
	 * 怪物资源id
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
	 * 怪物描述
	 */
    private final String describe;



	/**
	 * 怪物描述
	 */
	public String getDescribe() {
		return describe;
	}

	public MonsterTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
name = element.getChildText("name").trim();
quality = Integer.parseInt( element.getChildText("quality").trim() );
currentLv = Integer.parseInt( element.getChildText("currentLv").trim() );
hp = Integer.parseInt( element.getChildText("hp").trim() );
attack = Integer.parseInt( element.getChildText("attack").trim() );
strike = Integer.parseInt( element.getChildText("strike").trim() );
aspd = Integer.parseInt( element.getChildText("aspd").trim() );
speed = Integer.parseInt( element.getChildText("speed").trim() );
resId = element.getChildText("resId").trim();
barrage = Integer.parseInt( element.getChildText("barrage").trim() );
skill1 = Integer.parseInt( element.getChildText("skill1").trim() );
skill2 = Integer.parseInt( element.getChildText("skill2").trim() );
describe = element.getChildText("describe").trim();

	}

	@Override
	public String toString() {
		return "MonsterTemplet [id = " + id + ",name = " + name + ",quality = " + quality + ",currentLv = " + currentLv + ",hp = " + hp + ",attack = " + attack + ",strike = " + strike + ",aspd = " + aspd + ",speed = " + speed + ",resId = " + resId + ",barrage = " + barrage + ",skill1 = " + skill1 + ",skill2 = " + skill2 + ",describe = " + describe + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
