package com.hz.dafeiji.cfg.reward;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2015-1-7 16:45:27
 */
public class BaseRewardTemplet {

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
	 * 描述
	 */
    private final String describe;



	/**
	 * 描述
	 */
	public String getDescribe() {
		return describe;
	}/**
	 * 图标资源id
	 */
    private final int resid;



	/**
	 * 图标资源id
	 */
	public int getResid() {
		return resid;
	}

	public BaseRewardTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
name = element.getChildText("name").trim();
describe = element.getChildText("describe").trim();
resid = Integer.parseInt( element.getChildText("resid").trim() );

	}

	@Override
	public String toString() {
		return "BaseRewardTemplet [id = " + id + ",name = " + name + ",describe = " + describe + ",resid = " + resid + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
