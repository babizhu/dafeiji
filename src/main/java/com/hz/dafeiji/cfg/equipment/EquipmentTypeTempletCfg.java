package com.hz.dafeiji.cfg.equipment;

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
 * 2015-1-12 15:45:40
 */
public class EquipmentTypeTempletCfg {
	private static final Map<Integer,EquipmentTypeTemplet> equipmentTypeTemplets = new HashMap<>();


	static{
		//init();

	}
	private static final String FILE = "./resource/xml/equipment/equipmentType.xml";



	public static void init(){

		SAXBuilder builder = new SAXBuilder();
		Document document;
		try {
			document = builder.build( FILE );
			Element root = document.getRootElement();
			List<?> list = root.getChildren( "EquipmentType" );

			for (Object o : list) {
				EquipmentTypeTemplet templet = new EquipmentTypeTemplet( (Element) o );
				EquipmentTypeTemplet temp = equipmentTypeTemplets.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "EquipmentTypeTemplet id [" + temp.getId() + "] 重复了" );
				}

			}
		} catch (JDOMException | IOException e) {
		    e.printStackTrace();
        }

		System.out.println( "EquipmentTypeTemplet xml配置文件解析完毕" );
	}


	/**
	 * 通过id获取EquipmentTypeTemplet的引用
	 * @param   templetId   id
	 * @return  返回一个引用
	 */
	public static EquipmentTypeTemplet getEquipmentTypeTempletById( int templetId ){
		return equipmentTypeTemplets.get( templetId );
	}

	/*自定义代码开始*//*自定义代码结束*/

	public static void main(String[] args) {

		int id = 100001;
		System.out.println( getEquipmentTypeTempletById( id ) );
	}
}
