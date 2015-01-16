package com.hz.dafeiji.cfg.reward;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2015-1-16 15:10:13
 */
public class ActShopTemplet {

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
	 * 获得行动力
	 */
    private final int Sycee;



	/**
	 * 获得行动力
	 */
	public int getSycee() {
		return Sycee;
	}

	public ActShopTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
name = element.getChildText("name").trim();
diamond = Integer.parseInt( element.getChildText("diamond").trim() );
Sycee = Integer.parseInt( element.getChildText("Sycee").trim() );

	}

	@Override
	public String toString() {
		return "ActShopTemplet [id = " + id + ",name = " + name + ",diamond = " + diamond + ",Sycee = " + Sycee + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
