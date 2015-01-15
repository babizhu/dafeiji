package com.hz.dafeiji.cfg.skill;

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
 * 2015-1-15 14:14:16
 */
public class SikllTempletCfg {
	private static final Map<Integer,SikllTemplet> sikllTemplets = new HashMap<>();


	static{
		//init();

	}
	private static final String FILE = "./resource/xml/skill/sikll.xml";



	public static void init(){

		SAXBuilder builder = new SAXBuilder();
		Document document;
		try {
			document = builder.build( FILE );
			Element root = document.getRootElement();
			List<?> list = root.getChildren( "Sikll" );

			for (Object o : list) {
				SikllTemplet templet = new SikllTemplet( (Element) o );
				SikllTemplet temp = sikllTemplets.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "SikllTemplet id [" + temp.getId() + "] 重复了" );
				}

			}
		} catch (JDOMException | IOException e) {
		    e.printStackTrace();
        }

		System.out.println( "SikllTemplet xml配置文件解析完毕" );
	}


	/**
	 * 通过id获取SikllTemplet的引用
	 * @param   templetId   id
	 * @return  返回一个引用
	 */
	public static SikllTemplet getSikllTempletById( int templetId ){
		return sikllTemplets.get( templetId );
	}

	/*自定义代码开始*//*自定义代码结束*/

	public static void main(String[] args) {

		int id = 100001;
		System.out.println( getSikllTempletById( id ) );
	}
}
