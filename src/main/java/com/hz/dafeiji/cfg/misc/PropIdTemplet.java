package com.hz.dafeiji.cfg.misc;

import org.jdom2.Element;

/**
 * 模版
 *
 * @author liukun
 *         2015-1-4 13:44:26
 */
public class PropIdTemplet{

    /**
     * 名字
     */
    private final String name;


    /**
     * 名字
     */
    public String getName(){
        return name;
    }

    /**
     * id
     */
    private final int id;


    /**
     * id
     */
    public int getId(){
        return id;
    }

    /**
     * 值
     */
    private final String value;


    /**
     * 值
     */
    public String getValue(){
        return value;
    }

    /**
     * 说明
     */
    private final String explain;


    /**
     * 说明
     */
    public String getExplain(){
        return explain;
    }

    public PropIdTemplet( Element element ){
        name = element.getChildText( "name" ).trim();
        id = Integer.parseInt( element.getChildText( "id" ).trim() );
        value = element.getChildText( "value" ).trim();
        explain = element.getChildText( "explain" ).trim();

    }

    @Override
    public String toString(){
        return "PropIdTemplet [name = " + name + ",id = " + id + ",value = " + value + ",explain = " + explain + "]";
    }

	/*自定义代码开始*//*自定义代码结束*/
}
