package com.hz.dafeiji.cfg.reward;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2015-1-13 14:35:25
 */
public class DiamondShopTemplet {

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
	 * 充值金额（rmb）
	 */
    private final int Rmb;



	/**
	 * 充值金额（rmb）
	 */
	public int getRmb() {
		return Rmb;
	}/**
	 * 获得钻石
	 */
    private final int diamond;



	/**
	 * 获得钻石
	 */
	public int getDiamond() {
		return diamond;
	}

	public DiamondShopTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
name = element.getChildText("name").trim();
Rmb = Integer.parseInt( element.getChildText("Rmb").trim() );
diamond = Integer.parseInt( element.getChildText("diamond").trim() );

	}

	@Override
	public String toString() {
		return "DiamondShopTemplet [id = " + id + ",name = " + name + ",Rmb = " + Rmb + ",diamond = " + diamond + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
