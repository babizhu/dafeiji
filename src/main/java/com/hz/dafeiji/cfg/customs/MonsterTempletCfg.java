package com.hz.dafeiji.cfg.customs;

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
public class MonsterTempletCfg {
	private static final Map<Integer,MonsterTemplet> monsterTemplets = new HashMap<>();


	static{
		//init();

	}
	private static final String FILE = "./resource/xml/customs/monster.xml";



	public static void init(){

		SAXBuilder builder = new SAXBuilder();
		Document document;
		try {
			document = builder.build( FILE );
			Element root = document.getRootElement();
			List<?> list = root.getChildren( "Monster" );

			for (Object o : list) {
				MonsterTemplet templet = new MonsterTemplet( (Element) o );
				MonsterTemplet temp = monsterTemplets.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "MonsterTemplet id [" + temp.getId() + "] 重复了" );
				}

			}
		} catch (JDOMException | IOException e) {
		    e.printStackTrace();
        }

		System.out.println( "MonsterTemplet xml配置文件解析完毕" );
	}


	/**
	 * 通过id获取MonsterTemplet的引用
	 * @param   templetId   id
	 * @return  返回一个引用
	 */
	public static MonsterTemplet getMonsterTempletById( int templetId ){
		return monsterTemplets.get( templetId );
	}

	/*自定义代码开始*//*自定义代码结束*/

	public static void main(String[] args) {

		int id = 100001;
		System.out.println( getMonsterTempletById( id ) );
	}
}
