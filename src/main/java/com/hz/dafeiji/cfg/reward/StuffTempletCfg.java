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
 * 2015-1-16 15:10:13
 */
public class StuffTempletCfg {
	private static final Map<Integer,StuffTemplet> stuffTemplets = new HashMap<>();


	static{
		//init();

	}
	private static final String FILE = "./resource/xml/reward/stuff.xml";



	public static void init(){

		SAXBuilder builder = new SAXBuilder();
		Document document;
		try {
			document = builder.build( FILE );
			Element root = document.getRootElement();
			List<?> list = root.getChildren( "Stuff" );

			for (Object o : list) {
				StuffTemplet templet = new StuffTemplet( (Element) o );
				StuffTemplet temp = stuffTemplets.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "StuffTemplet id [" + temp.getId() + "] 重复了" );
				}

			}
		} catch (JDOMException | IOException e) {
		    e.printStackTrace();
        }

		System.out.println( "StuffTemplet xml配置文件解析完毕" );
	}


	/**
	 * 通过id获取StuffTemplet的引用
	 * @param   templetId   id
	 * @return  返回一个引用
	 */
	public static StuffTemplet getStuffTempletById( int templetId ){
		return stuffTemplets.get( templetId );
	}

	/*自定义代码开始*//*自定义代码结束*/

	public static void main(String[] args) {

		int id = 100001;
		System.out.println( getStuffTempletById( id ) );
	}
}
