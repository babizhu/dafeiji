package com.hz.dafeiji.cfg.manual.define;

import com.google.common.collect.Maps;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * user         LIUKUN
 * time         2015-1-8 10:06
 */

public class DefineTempletConfig{
    private static Map<String, DefineTemplet> map = Maps.newConcurrentMap();
//    private static List<String> keys;
//    private static List<DefineTemplet> all;


    static{
//        load();
    }

//    public static List<DefineTemplet> getAll() {
//        return new ArrayList<DefineTemplet>(all);
//    }
//
//    public static List<String> getKeys() {
//        return keys;
//    }

    private static final String FILE = "./resource/xml/define/DefineConfig.xml";

    public static void init(){

        SAXBuilder builder = new SAXBuilder();
        Document document;
        try {
            document = builder.build( FILE );
            Element root = document.getRootElement();
            List<?> list = root.getChildren( "items" );

            System.out.println( list.size() );
            for( Object o : list ) {
//
                DefineTemplet templet = new DefineTemplet( (Element) o );
                DefineTemplet temp = map.put( templet.getName(), templet );
                if( temp != null ) {
                    throw new RuntimeException( "DefineTemplet name [" + temp.getName() + "] 重复了" );
                }
                System.out.println( templet );
            }
        } catch( JDOMException | IOException e ) {
            e.printStackTrace();
        }

        System.out.println( "DefineTemplet xml配置文件解析完毕" );
    }


    public static DefineTemplet get( String x ){
        synchronized( map ) {
            return map.get( x );
        }
    }

    public static void main( String[] args ){
        init();
        System.out.println( get( "BOSS_SKILL" ) );
    }
}

