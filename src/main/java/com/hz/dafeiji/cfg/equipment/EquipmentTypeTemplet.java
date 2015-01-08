package com.hz.dafeiji.cfg.equipment;

import org.jdom2.Element;

/**
 * 模版
 *
 * @author liukun
 *         2015-1-8 15:13:57
 */
public class EquipmentTypeTemplet{

    /**
     * 类型id
     */
    private final int id;


    /**
     * 类型id
     */
    public int getId(){
        return id;
    }

    /**
     * 对应装备类型
     */
    private final String typeName;


    /**
     * 对应装备类型
     */
    public String getTypeName(){
        return typeName;
    }

    /**
     * 对升级所影响比例
     */
    private final float ratioUp;


    /**
     * 对升级所影响比例
     */
    public float getRatioUp(){
        return ratioUp;
    }

    /**
     * 对拆分所影响比例
     */
    private final float ratioSplit;


    /**
     * 对拆分所影响比例
     */
    public float getRatioSplit(){
        return ratioSplit;
    }

    public EquipmentTypeTemplet( Element element ){
        id = Integer.parseInt( element.getChildText( "id" ).trim() );
        typeName = element.getChildText( "typeName" ).trim();
        ratioUp = Float.parseFloat( element.getChildText( "ratioUp" ).trim() );
        ratioSplit = Float.parseFloat( element.getChildText( "ratioSplit" ).trim() );

    }

    @Override
    public String toString(){
        return "EquipmentTypeTemplet [id = " + id + ",typeName = " + typeName + ",ratioUp = " + ratioUp + ",ratioSplit = " + ratioSplit + "]";
    }

	/*自定义代码开始*//*自定义代码结束*/
}
