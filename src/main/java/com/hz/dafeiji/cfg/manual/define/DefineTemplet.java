package com.hz.dafeiji.cfg.manual.define;

import lombok.Data;
import org.jdom2.Element;

/**
 * user         LIUKUN
 * time         2015-1-8 10:07
 */
@Data
public class DefineTemplet{
    /**
     * 名字
     */
    private String name;
    /**
     * 資料類型
     */
    private String type;
    /**
     * 值
     */
    private String value;
    /**
     * 說明
     */
    private String explain;

    public DefineTemplet( Element o ){
        name = o.getAttribute( "name" ).getValue();
        type = o.getAttribute( "type" ).getValue();
        value = o.getAttribute( "value" ).getValue();
        explain = o.getAttribute( "explain" ).getValue();
    }
//
//    /**
//     * 名字
//     */
//    void setName( String name ){
//        this.name = name;
//    }
//
//    /**
//     * 名字
//     */
//    public String getName(){
//        return this.name;
//    }
//
//    /**
//     * 資料類型
//     */
//    void setType( String type ){
//        this.type = type;
//    }
//
//    /**
//     * 資料類型
//     */
//    public String getType(){
//        return this.type;
//    }
//
//    /**
//     * 值
//     */
//    void setValue( String value ){
//        this.value = value;
//    }
//
//    /**
//     * 值
//     */
//    public String getValue(){
//        return this.value;
//    }
//
//    /**
//     * 說明
//     */
//    void setExplain( String explain ){
//        this.explain = explain;
//    }
//
//    /**
//     * 說明
//     */
//    public String getExplain(){
//        return this.explain;
//    }

}
