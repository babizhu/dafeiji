package com.hz.dafeiji.cfg.wing;

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
 * 2015-1-13 14:35:25
 */
public class WingQurlityTempletCfg {
	private static final Map<Integer,WingQurlityTemplet> wingQurlityTemplets = new HashMap<>();


	static{
		//init();

	}
	private static final String FILE = "./resource/xml/wing/wingQurlity.xml";



	public static void init(){

		SAXBuilder builder = new SAXBuilder();
		Document document;
		try {
			document = builder.build( FILE );
			Element root = document.getRootElement();
			List<?> list = root.getChildren( "WingQurlity" );

			for (Object o : list) {
				WingQurlityTemplet templet = new WingQurlityTemplet( (Element) o );
				WingQurlityTemplet temp = wingQurlityTemplets.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "WingQurlityTemplet id [" + temp.getId() + "] 重复了" );
				}

			}
		} catch (JDOMException | IOException e) {
		    e.printStackTrace();
        }

		System.out.println( "WingQurlityTemplet xml配置文件解析完毕" );
	}


	/**
	 * 通过id获取WingQurlityTemplet的引用
	 * @param   templetId   id
	 * @return  返回一个引用
	 */
	public static WingQurlityTemplet getWingQurlityTempletById( int templetId ){
		return wingQurlityTemplets.get( templetId );
	}

	/*自定义代码开始*//*自定义代码结束*/

	public static void main(String[] args) {

		int id = 100001;
		System.out.println( getWingQurlityTempletById( id ) );
	}
}
