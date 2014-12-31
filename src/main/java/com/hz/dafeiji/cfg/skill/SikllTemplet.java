package com.hz.dafeiji.cfg.skill;

import org.jdom2.Element;

/**
 * 模版
 *
 * @author liukun
 *         2014-12-29 17:29:32
 */
public class SikllTemplet{

    /**
     * 技能id
     */
    private final int id;


    /**
     * 技能id
     */
    public int getId(){
        return id;
    }

    /**
     * 技能名称
     */
    private final String name;


    /**
     * 技能名称
     */
    public String getName(){
        return name;
    }

    /**
     * 技能用处（也是属于僚机2还是装备1）
     */
    private final int purpose;


    /**
     * 技能用处（也是属于僚机2还是装备1）
     */
    public int getPurpose(){
        return purpose;
    }

    /**
     * 技能分类（1攻击类 2防御类 3收益类 4特性类 5概率类 6道具类 7触发类）
     */
    private final int type;


    /**
     * 技能分类（1攻击类 2防御类 3收益类 4特性类 5概率类 6道具类 7触发类）
     */
    public int getType(){
        return type;
    }

    /**
     * 技能描述
     */
    private final String describe;


    /**
     * 技能描述
     */
    public String getDescribe(){
        return describe;
    }

    public SikllTemplet( Element element ){
        id = Integer.parseInt( element.getChildText( "id" ).trim() );
        name = element.getChildText( "name" ).trim();
        purpose = Integer.parseInt( element.getChildText( "purpose" ).trim() );
        type = Integer.parseInt( element.getChildText( "type" ).trim() );
        describe = element.getChildText( "describe" ).trim();

    }

    @Override
    public String toString(){
        return "SikllTemplet [id = " + id + ",name = " + name + ",purpose = " + purpose + ",type = " + type + ",describe = " + describe + "]";
    }

	/*自定义代码开始*//*自定义代码结束*/
}
