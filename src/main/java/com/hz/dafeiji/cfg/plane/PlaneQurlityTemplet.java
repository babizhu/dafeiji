package com.hz.dafeiji.cfg.plane;

import org.jdom2.Element;

/**
 * 模版
 *
 * @author liukun
 *         2014-12-29 17:29:32
 */
public class PlaneQurlityTemplet{

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
     * 品阶id
     */
    private final int quality;


    /**
     * 品阶id
     */
    public int getQuality(){
        return quality;
    }

    /**
     * 品阶等级
     */
    private final int step;


    /**
     * 品阶等级
     */
    public int getStep(){
        return step;
    }

    /**
     * 实星数
     */
    private final int starCount;


    /**
     * 实星数
     */
    public int getStarCount(){
        return starCount;
    }

    /**
     * 最大星星数
     */
    private final int starNumber;


    /**
     * 最大星星数
     */
    public int getStarNumber(){
        return starNumber;
    }

    /**
     * 所能升级的最大等级
     */
    private final int maxLv;


    /**
     * 所能升级的最大等级
     */
    public int getMaxLv(){
        return maxLv;
    }

    /**
     * 品阶消耗系数
     */
    private final float consumeFactor;


    /**
     * 品阶消耗系数
     */
    public float getConsumeFactor(){
        return consumeFactor;
    }

    /**
     * 升级基础消耗金币
     */
    private final int consumeBasis;


    /**
     * 升级基础消耗金币
     */
    public int getConsumeBasis(){
        return consumeBasis;
    }

    /**
     * 说明
     */
    private final String state;


    /**
     * 说明
     */
    public String getState(){
        return state;
    }

    public PlaneQurlityTemplet( Element element ){
        id = Integer.parseInt( element.getChildText( "id" ).trim() );
        quality = Integer.parseInt( element.getChildText( "quality" ).trim() );
        step = Integer.parseInt( element.getChildText( "step" ).trim() );
        starCount = Integer.parseInt( element.getChildText( "starCount" ).trim() );
        starNumber = Integer.parseInt( element.getChildText( "starNumber" ).trim() );
        maxLv = Integer.parseInt( element.getChildText( "maxLv" ).trim() );
        consumeFactor = Float.parseFloat( element.getChildText( "consumeFactor" ).trim() );
        consumeBasis = Integer.parseInt( element.getChildText( "consumeBasis" ).trim() );
        state = element.getChildText( "state" ).trim();

    }

    @Override
    public String toString(){
        return "PlaneQurlityTemplet [id = " + id + ",quality = " + quality + ",step = " + step + ",starCount = " + starCount + ",starNumber = " + starNumber + ",maxLv = " + maxLv + ",consumeFactor = " + consumeFactor + ",consumeBasis = " + consumeBasis + ",state = " + state + "]";
    }

	/*自定义代码开始*//*自定义代码结束*/
}
