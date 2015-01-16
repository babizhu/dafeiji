package com.hz.dafeiji.cfg.reward;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2015-1-16 15:10:13
 */
public class StuffTemplet {

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
	 * 显示顺序
	 */
    private final int order;



	/**
	 * 显示顺序
	 */
	public int getOrder() {
		return order;
	}/**
	 * 道具类型(1战斗前 2战斗中 3奖励,4僚机碎片 5装备碎片)
	 */
    private final int type;



	/**
	 * 道具类型(1战斗前 2战斗中 3奖励,4僚机碎片 5装备碎片)
	 */
	public int getType() {
		return type;
	}/**
	 * 品质
	 */
    private final int quality;



	/**
	 * 品质
	 */
	public int getQuality() {
		return quality;
	}/**
	 * 道具的具体效果
	 */
    private final String awards;



	/**
	 * 道具的具体效果
	 */
	public String getAwards() {
		return awards;
	}/**
	 * 物品用途
	 */
    private final String purpose;



	/**
	 * 物品用途
	 */
	public String getPurpose() {
		return purpose;
	}/**
	 * 物品来源
	 */
    private final String source;



	/**
	 * 物品来源
	 */
	public String getSource() {
		return source;
	}/**
	 * 叠加数量
	 */
    private final int addUp;



	/**
	 * 叠加数量
	 */
	public int getAddUp() {
		return addUp;
	}/**
	 * 买价(-1表示不卖)
	 */
    private final String sellPrice;



	/**
	 * 买价(-1表示不卖)
	 */
	public String getSellPrice() {
		return sellPrice;
	}/**
	 * 卖价描述
	 */
    private final String sellPriceDs;



	/**
	 * 卖价描述
	 */
	public String getSellPriceDs() {
		return sellPriceDs;
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
	 * 描述
	 */
    private final String describe;



	/**
	 * 描述
	 */
	public String getDescribe() {
		return describe;
	}

	public StuffTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
name = element.getChildText("name").trim();
order = Integer.parseInt( element.getChildText("order").trim() );
type = Integer.parseInt( element.getChildText("type").trim() );
quality = Integer.parseInt( element.getChildText("quality").trim() );
awards = element.getChildText("awards").trim();
purpose = element.getChildText("purpose").trim();
source = element.getChildText("source").trim();
addUp = Integer.parseInt( element.getChildText("addUp").trim() );
sellPrice = element.getChildText("sellPrice").trim();
sellPriceDs = element.getChildText("sellPriceDs").trim();
resId = element.getChildText("resId").trim();
describe = element.getChildText("describe").trim();

	}

	@Override
	public String toString() {
		return "StuffTemplet [id = " + id + ",name = " + name + ",order = " + order + ",type = " + type + ",quality = " + quality + ",awards = " + awards + ",purpose = " + purpose + ",source = " + source + ",addUp = " + addUp + ",sellPrice = " + sellPrice + ",sellPriceDs = " + sellPriceDs + ",resId = " + resId + ",describe = " + describe + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
