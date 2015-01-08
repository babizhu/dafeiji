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
 *
 * @author liukun
 *         2015-1-8 15:13:57
 */
public class EquipmentExpTempletCfg{
    private static final Map<Integer, EquipmentExpTemplet> equipmentExpTemplets = new HashMap<>();


    static{
        //init();

    }

    private static final String FILE = "./resource/xml/equipment/equipmentExp.xml";


    public static void init(){

        SAXBuilder builder = new SAXBuilder();
        Document document;
        try {
            document = builder.build( FILE );
            Element root = document.getRootElement();
            List<?> list = root.getChildren( "EquipmentExp" );

            for( Object o : list ) {
                EquipmentExpTemplet templet = new EquipmentExpTemplet( (Element) o );
                EquipmentExpTemplet temp = equipmentExpTemplets.put( templet.getId(), templet );
                if( temp != null ) {
                    throw new RuntimeException( "EquipmentExpTemplet id [" + temp.getId() + "] 重复了" );
                }

            }
        } catch( JDOMException | IOException e ) {
            e.printStackTrace();
        }

        System.out.println( "EquipmentExpTemplet xml配置文件解析完毕" );
    }


    /**
     * 通过id获取EquipmentExpTemplet的引用
     *
     * @param templetId id
     * @return 返回一个引用
     */
    public static EquipmentExpTemplet getEquipmentExpTempletById( int templetId ){
        return equipmentExpTemplets.get( templetId );
    }

	/*自定义代码开始*//*自定义代码结束*/

    public static void main( String[] args ){

        int id = 100001;
        System.out.println( getEquipmentExpTempletById( id ) );
    }
}
