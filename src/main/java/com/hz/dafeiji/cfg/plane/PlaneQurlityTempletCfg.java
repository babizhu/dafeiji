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
 *
 * @author liukun
 *         2014-12-29 17:29:32
 */
public class PlaneQurlityTempletCfg{
    private static final Map<Integer, PlaneQurlityTemplet> planeQurlityTemplets = new HashMap<>();


    static{
        //init();

    }

    private static final String FILE = "./resource/xml/plane/planeQurlity.xml";


    public static void init(){

        SAXBuilder builder = new SAXBuilder();
        Document document;
        try {
            document = builder.build( FILE );
            Element root = document.getRootElement();
            List<?> list = root.getChildren( "PlaneQurlity" );

            for( Object o : list ) {
                PlaneQurlityTemplet templet = new PlaneQurlityTemplet( (Element) o );
                PlaneQurlityTemplet temp = planeQurlityTemplets.put( templet.getId(), templet );
                if( temp != null ) {
                    throw new RuntimeException( "PlaneQurlityTemplet id [" + temp.getId() + "] 重复了" );
                }

            }
        } catch( JDOMException | IOException e ) {
            e.printStackTrace();
        }

        System.out.println( "PlaneQurlityTemplet xml配置文件解析完毕" );
    }


    /**
     * 通过id获取PlaneQurlityTemplet的引用
     *
     * @param templetId id
     * @return 返回一个引用
     */
    public static PlaneQurlityTemplet getPlaneQurlityTempletById( int templetId ){
        return planeQurlityTemplets.get( templetId );
    }

	/*自定义代码开始*//*自定义代码结束*/

    public static void main( String[] args ){

        int id = 100001;
        System.out.println( getPlaneQurlityTempletById( id ) );
    }
}
