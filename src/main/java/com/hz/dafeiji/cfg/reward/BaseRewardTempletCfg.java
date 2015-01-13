package com.hz.dafeiji.cfg.reward;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模版配置
 * @author liukun
 * 2015-1-13 13:52:08
 */
public class BaseRewardTempletCfg {
	private static final Map<Integer,BaseRewardTemplet> baseRewardTemplets = new HashMap<>();


	static{
		//init();

	}
	private static final String FILE = "./resource/xml/reward/baseReward.xml";



	public static void init(){

		SAXBuilder builder = new SAXBuilder();
		Document document;
		try {
			document = builder.build( FILE );
			Element root = document.getRootElement();
			List<?> list = root.getChildren( "BaseReward" );

			for (Object o : list) {
				BaseRewardTemplet templet = new BaseRewardTemplet( (Element) o );
				BaseRewardTemplet temp = baseRewardTemplets.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "BaseRewardTemplet id [" + temp.getId() + "] 重复了" );
				}

			}
		} catch (JDOMException | IOException e) {
		    e.printStackTrace();
        }

		System.out.println( "BaseRewardTemplet xml配置文件解析完毕" );
	}


	/**
	 * 通过id获取BaseRewardTemplet的引用
	 * @param   templetId   id
	 * @return  返回一个引用
	 */
	public static BaseRewardTemplet getBaseRewardTempletById( int templetId ){
		return baseRewardTemplets.get( templetId );
	}

	/*自定义代码开始*//*自定义代码结束*/

	public static void main(String[] args) {

		int id = 100001;
		System.out.println( getBaseRewardTempletById( id ) );
	}
}
