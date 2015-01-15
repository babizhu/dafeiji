package com.hz.dafeiji.cfg.reward;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2015-1-15 17:31:53
 */
public class GoldShopTemplet {

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
	 * 名稱
	 */
    private final String name;



	/**
	 * 名稱
	 */
	public String getName() {
		return name;
	}/**
	 * 花费钻石
	 */
    private final int diamond;



	/**
	 * 花费钻石
	 */
	public int getDiamond() {
		return diamond;
	}/**
	 * 获得金币
	 */
    private final int gold;



	/**
	 * 获得金币
	 */
	public int getGold() {
		return gold;
	}

	public GoldShopTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
name = element.getChildText("name").trim();
diamond = Integer.parseInt( element.getChildText("diamond").trim() );
gold = Integer.parseInt( element.getChildText("gold").trim() );

	}

	@Override
	public String toString() {
		return "GoldShopTemplet [id = " + id + ",name = " + name + ",diamond = " + diamond + ",gold = " + gold + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
