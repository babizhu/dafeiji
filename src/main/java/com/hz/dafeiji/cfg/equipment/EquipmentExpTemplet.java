package com.hz.dafeiji.cfg.equipment;

import org.jdom2.Element;

/**
 * 模版
 *
 * @author liukun
 *         2015-1-8 17:18:10
 */
public class EquipmentExpTemplet{

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
     * 等级
     */
    private final int lv;


    /**
     * 等级
     */
    public int getLv(){
        return lv;
    }

    /**
     * 1星星（升级所需能源）
     */
    private final int expUp1;


    /**
     * 1星星（升级所需能源）
     */
    public int getExpUp1(){
        return expUp1;
    }

    /**
     * 2星星（升级所需能源）
     */
    private final int expUp2;


    /**
     * 2星星（升级所需能源）
     */
    public int getExpUp2(){
        return expUp2;
    }

    /**
     * 3星星（升级所需能源）
     */
    private final int expUp3;


    /**
     * 3星星（升级所需能源）
     */
    public int getExpUp3(){
        return expUp3;
    }

    /**
     * 4星星（升级所需能源）
     */
    private final int expUp4;


    /**
     * 4星星（升级所需能源）
     */
    public int getExpUp4(){
        return expUp4;
    }

    /**
     * 1月亮（升级所需能源）
     */
    private final int expUp5;


    /**
     * 1月亮（升级所需能源）
     */
    public int getExpUp5(){
        return expUp5;
    }

    /**
     * 2月亮（升级所需能源）
     */
    private final int expUp6;


    /**
     * 2月亮（升级所需能源）
     */
    public int getExpUp6(){
        return expUp6;
    }

    /**
     * 3月亮（升级所需能源）
     */
    private final int expUp7;


    /**
     * 3月亮（升级所需能源）
     */
    public int getExpUp7(){
        return expUp7;
    }

    /**
     * 4月亮（升级所需能源）
     */
    private final int expUp8;


    /**
     * 4月亮（升级所需能源）
     */
    public int getExpUp8(){
        return expUp8;
    }

    /**
     * 1太阳（升级所需能源）
     */
    private final int expUp9;


    /**
     * 1太阳（升级所需能源）
     */
    public int getExpUp9(){
        return expUp9;
    }

    /**
     * 2太阳（升级所需能源）
     */
    private final int expUp10;


    /**
     * 2太阳（升级所需能源）
     */
    public int getExpUp10(){
        return expUp10;
    }

    /**
     * 3太阳（升级所需能源）
     */
    private final int expUp11;


    /**
     * 3太阳（升级所需能源）
     */
    public int getExpUp11(){
        return expUp11;
    }

    /**
     * 4太阳（升级所需能源）
     */
    private final int expUp12;


    /**
     * 4太阳（升级所需能源）
     */
    public int getExpUp12(){
        return expUp12;
    }

    /**
     * 1星星（拆分所得能源）
     */
    private final int expPh1;


    /**
     * 1星星（拆分所得能源）
     */
    public int getExpPh1(){
        return expPh1;
    }

    /**
     * 2星星（拆分所得能源）
     */
    private final int expPh2;


    /**
     * 2星星（拆分所得能源）
     */
    public int getExpPh2(){
        return expPh2;
    }

    /**
     * 3星星（拆分所得能源）
     */
    private final int expPh3;


    /**
     * 3星星（拆分所得能源）
     */
    public int getExpPh3(){
        return expPh3;
    }

    /**
     * 4星星（拆分所得能源）
     */
    private final int expPh4;


    /**
     * 4星星（拆分所得能源）
     */
    public int getExpPh4(){
        return expPh4;
    }

    /**
     * 1月亮（拆分所得能源）
     */
    private final int expPh5;


    /**
     * 1月亮（拆分所得能源）
     */
    public int getExpPh5(){
        return expPh5;
    }

    /**
     * 2月亮（拆分所得能源）
     */
    private final int expPh6;


    /**
     * 2月亮（拆分所得能源）
     */
    public int getExpPh6(){
        return expPh6;
    }

    /**
     * 3月亮（拆分所得能源）
     */
    private final int expPh7;


    /**
     * 3月亮（拆分所得能源）
     */
    public int getExpPh7(){
        return expPh7;
    }

    /**
     * 4月亮（拆分所得能源）
     */
    private final int expPh8;


    /**
     * 4月亮（拆分所得能源）
     */
    public int getExpPh8(){
        return expPh8;
    }

    /**
     * 1太阳（拆分所得能源）
     */
    private final int expPh9;


    /**
     * 1太阳（拆分所得能源）
     */
    public int getExpPh9(){
        return expPh9;
    }

    /**
     * 2太阳（拆分所得能源）
     */
    private final int expPh10;


    /**
     * 2太阳（拆分所得能源）
     */
    public int getExpPh10(){
        return expPh10;
    }

    /**
     * 3太阳（拆分所得能源）
     */
    private final int expPh11;


    /**
     * 3太阳（拆分所得能源）
     */
    public int getExpPh11(){
        return expPh11;
    }

    /**
     * 4太阳（拆分所得能源）
     */
    private final int expPh12;


    /**
     * 4太阳（拆分所得能源）
     */
    public int getExpPh12(){
        return expPh12;
    }

    public EquipmentExpTemplet( Element element ){
        id = Integer.parseInt( element.getChildText( "id" ).trim() );
        lv = Integer.parseInt( element.getChildText( "lv" ).trim() );
        expUp1 = Integer.parseInt( element.getChildText( "expUp1" ).trim() );
        expUp2 = Integer.parseInt( element.getChildText( "expUp2" ).trim() );
        expUp3 = Integer.parseInt( element.getChildText( "expUp3" ).trim() );
        expUp4 = Integer.parseInt( element.getChildText( "expUp4" ).trim() );
        expUp5 = Integer.parseInt( element.getChildText( "expUp5" ).trim() );
        expUp6 = Integer.parseInt( element.getChildText( "expUp6" ).trim() );
        expUp7 = Integer.parseInt( element.getChildText( "expUp7" ).trim() );
        expUp8 = Integer.parseInt( element.getChildText( "expUp8" ).trim() );
        expUp9 = Integer.parseInt( element.getChildText( "expUp9" ).trim() );
        expUp10 = Integer.parseInt( element.getChildText( "expUp10" ).trim() );
        expUp11 = Integer.parseInt( element.getChildText( "expUp11" ).trim() );
        expUp12 = Integer.parseInt( element.getChildText( "expUp12" ).trim() );
        expPh1 = Integer.parseInt( element.getChildText( "expPh1" ).trim() );
        expPh2 = Integer.parseInt( element.getChildText( "expPh2" ).trim() );
        expPh3 = Integer.parseInt( element.getChildText( "expPh3" ).trim() );
        expPh4 = Integer.parseInt( element.getChildText( "expPh4" ).trim() );
        expPh5 = Integer.parseInt( element.getChildText( "expPh5" ).trim() );
        expPh6 = Integer.parseInt( element.getChildText( "expPh6" ).trim() );
        expPh7 = Integer.parseInt( element.getChildText( "expPh7" ).trim() );
        expPh8 = Integer.parseInt( element.getChildText( "expPh8" ).trim() );
        expPh9 = Integer.parseInt( element.getChildText( "expPh9" ).trim() );
        expPh10 = Integer.parseInt( element.getChildText( "expPh10" ).trim() );
        expPh11 = Integer.parseInt( element.getChildText( "expPh11" ).trim() );
        expPh12 = Integer.parseInt( element.getChildText( "expPh12" ).trim() );

    }

    @Override
    public String toString(){
        return "EquipmentExpTemplet [id = " + id + ",lv = " + lv + ",expUp1 = " + expUp1 + ",expUp2 = " + expUp2 + ",expUp3 = " + expUp3 + ",expUp4 = " + expUp4 + ",expUp5 = " + expUp5 + ",expUp6 = " + expUp6 + ",expUp7 = " + expUp7 + ",expUp8 = " + expUp8 + ",expUp9 = " + expUp9 + ",expUp10 = " + expUp10 + ",expUp11 = " + expUp11 + ",expUp12 = " + expUp12 + ",expPh1 = " + expPh1 + ",expPh2 = " + expPh2 + ",expPh3 = " + expPh3 + ",expPh4 = " + expPh4 + ",expPh5 = " + expPh5 + ",expPh6 = " + expPh6 + ",expPh7 = " + expPh7 + ",expPh8 = " + expPh8 + ",expPh9 = " + expPh9 + ",expPh10 = " + expPh10 + ",expPh11 = " + expPh11 + ",expPh12 = " + expPh12 + "]";
    }

	/*自定义代码开始*//*自定义代码结束*/
}
