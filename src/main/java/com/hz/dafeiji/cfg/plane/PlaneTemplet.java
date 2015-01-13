package com.hz.dafeiji.cfg.plane;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2015-1-13 13:52:08
 */
public class PlaneTemplet {

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
	 * 初始生命
	 */
    private final int hp;



	/**
	 * 初始生命
	 */
	public int getHp() {
		return hp;
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
	 * 攻速
	 */
    private final int aspd;



	/**
	 * 攻速
	 */
	public int getAspd() {
		return aspd;
	}/**
	 * 生命成长
	 */
    private final float hpUp;



	/**
	 * 生命成长
	 */
	public float getHpUp() {
		return hpUp;
	}/**
	 * 攻击成长
	 */
    private final float attackUp;



	/**
	 * 攻击成长
	 */
	public float getAttackUp() {
		return attackUp;
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
	 * 获得条件
	 */
    private final String price;



	/**
	 * 获得条件
	 */
	public String getPrice() {
		return price;
	}/**
	 * 机甲描述
	 */
    private final String describe;



	/**
	 * 机甲描述
	 */
	public String getDescribe() {
		return describe;
	}

	public PlaneTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
name = element.getChildText("name").trim();
quality = Integer.parseInt( element.getChildText("quality").trim() );
currentLv = Integer.parseInt( element.getChildText("currentLv").trim() );
hp = Integer.parseInt( element.getChildText("hp").trim() );
attack = Integer.parseInt( element.getChildText("attack").trim() );
aspd = Integer.parseInt( element.getChildText("aspd").trim() );
hpUp = Float.parseFloat( element.getChildText("hpUp").trim() );
attackUp = Float.parseFloat( element.getChildText("attackUp").trim() );
skill1 = Integer.parseInt( element.getChildText("skill1").trim() );
skill2 = Integer.parseInt( element.getChildText("skill2").trim() );
resId = element.getChildText("resId").trim();
barrage = Integer.parseInt( element.getChildText("barrage").trim() );
price = element.getChildText("price").trim();
describe = element.getChildText("describe").trim();

	}

	@Override
	public String toString() {
		return "PlaneTemplet [id = " + id + ",name = " + name + ",quality = " + quality + ",currentLv = " + currentLv + ",hp = " + hp + ",attack = " + attack + ",aspd = " + aspd + ",hpUp = " + hpUp + ",attackUp = " + attackUp + ",skill1 = " + skill1 + ",skill2 = " + skill2 + ",resId = " + resId + ",barrage = " + barrage + ",price = " + price + ",describe = " + describe + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
