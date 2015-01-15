package com.hz.dafeiji.cfg.plane;

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
public class PlaneTempletCfg {
	private static final Map<Integer,PlaneTemplet> planeTemplets = new HashMap<>();


	static{
		//init();

	}
	private static final String FILE = "./resource/xml/plane/plane.xml";



	public static void init(){

		SAXBuilder builder = new SAXBuilder();
		Document document;
		try {
			document = builder.build( FILE );
			Element root = document.getRootElement();
			List<?> list = root.getChildren( "Plane" );

			for (Object o : list) {
				PlaneTemplet templet = new PlaneTemplet( (Element) o );
				PlaneTemplet temp = planeTemplets.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "PlaneTemplet id [" + temp.getId() + "] 重复了" );
				}

			}
		} catch (JDOMException | IOException e) {
		    e.printStackTrace();
        }

		System.out.println( "PlaneTemplet xml配置文件解析完毕" );
	}


	/**
	 * 通过id获取PlaneTemplet的引用
	 * @param   templetId   id
	 * @return  返回一个引用
	 */
	public static PlaneTemplet getPlaneTempletById( int templetId ){
		return planeTemplets.get( templetId );
	}

	/*自定义代码开始*//*自定义代码结束*/

	public static void main(String[] args) {

		int id = 100001;
		System.out.println( getPlaneTempletById( id ) );
	}
}
