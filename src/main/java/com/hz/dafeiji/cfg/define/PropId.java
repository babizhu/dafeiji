package com.hz.dafeiji.cfg.define;

import com.hz.dafeiji.cfg.manual.define.DefineTempletCfg;

/**
 * 常数定义，自动生成，勿改
 *
 * @author liukun
 *         2015-1-8 17:18:10
 */
@SuppressWarnings("UnusedDeclaration")
public class PropId{

    /**
     * 金币:游戏币，用于游戏日常消耗等。 *
     */
    public static int CASH_JIN_BI = 500001;
    /**
     * 钻石:充值获得货币. *
     */
    public static int CASH_ZUAN_SHI = 500002;
    /**
     * 总积分:总的积分数，只记录最高的 *
     */
    public static int ZONG_JI_FEN = 500003;
    /**
     * 周积分:战斗过程中获得的积分，用于排名（每周会清零） *
     */
    public static int ZHOU_JI_FEN = 500004;
    /**
     * 活动积分:用于兑换某些机甲或者是其他的道具 *
     */
    public static int HUO_DONG_JI_FEN = 500005;
    /**
     * 战斗力:玩家的整体实力体现 *
     */
    public static int ZHAN_DOU_LI = 500006;
    /**
     * 行动力:参与战斗的时候所消耗 *
     */
    public static int XING_DONG_LI = 500007;

    public static void reload(){
        DefineTempletCfg.init();
        CASH_JIN_BI = getInt( "CASH_JIN_BI" );
        CASH_ZUAN_SHI = getInt( "CASH_ZUAN_SHI" );
        ZONG_JI_FEN = getInt( "ZONG_JI_FEN" );
        ZHOU_JI_FEN = getInt( "ZHOU_JI_FEN" );
        HUO_DONG_JI_FEN = getInt( "HUO_DONG_JI_FEN" );
        ZHAN_DOU_LI = getInt( "ZHAN_DOU_LI" );
        XING_DONG_LI = getInt( "XING_DONG_LI" );
    }

    private static int getInt( String s ){
        return new Integer( get( s ) );
    }

    private static float getFloat( String s ){
        return new Float( get( s ) );
    }

    private static Boolean getBoolean( String s ){
        return Boolean.valueOf( get( s ) );
    }

    private static String getString( String s ){
        String string = get( s );
        if( string.endsWith( "\"" ) && string.startsWith( "\"" ) ) {
            string = string.substring( 1, string.length() - 1 );
        }
        return string;
    }

    private static String get( String s ){
        return DefineTempletCfg.get( s ).getValue().trim();
    }

    private static double getDouble( String s ){
        return new Double( get( s ) );
    }
}