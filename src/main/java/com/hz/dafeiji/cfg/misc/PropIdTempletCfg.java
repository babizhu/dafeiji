package com.hz.dafeiji.cfg.misc;

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
 *         2015-1-4 13:44:26
 */
public class PropIdTempletCfg{
    private static final Map<Integer, PropIdTemplet> propIdTemplets = new HashMap<>();


    static{
        //init();

    }

    private static final String FILE = "./resource/xml/misc/propId.xml";


    public static void init(){

        SAXBuilder builder = new SAXBuilder();
        Document document;
        try {
            document = builder.build( FILE );
            Element root = document.getRootElement();
            List<?> list = root.getChildren( "PropId" );

            for( Object o : list ) {
                PropIdTemplet templet = new PropIdTemplet( (Element) o );
                PropIdTemplet temp = propIdTemplets.put( templet.getId(), templet );
                if( temp != null ) {
                    throw new RuntimeException( "PropIdTemplet id [" + temp.getId() + "] 重复了" );
                }

            }
        } catch( JDOMException | IOException e ) {
            e.printStackTrace();
        }

        System.out.println( "PropIdTemplet xml配置文件解析完毕" );
    }


    /**
     * 通过id获取PropIdTemplet的引用
     *
     * @param templetId id
     * @return 返回一个引用
     */
    public static PropIdTemplet getPropIdTempletById( int templetId ){
        return propIdTemplets.get( templetId );
    }

	/*自定义代码开始*//*自定义代码结束*/

    public static void main( String[] args ){

        int id = 100001;
        System.out.println( getPropIdTempletById( id ) );
    }
}