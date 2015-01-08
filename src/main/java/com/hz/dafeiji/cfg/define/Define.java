package com.hz.dafeiji.cfg.define;

import com.hz.dafeiji.cfg.manual.define.DefineTempletCfg;

/**
 * 常数定义，自动生成，勿改
 * @author liukun
 * 2015-1-8 17:43:15
 */
@SuppressWarnings("UnusedDeclaration")
public class Define{

    /**
     * 装备升级所消耗的金币与能源所占比例 *
     */
    public static float JIN_BI_EQUIPMENT_UP = 0.1F;
    /**
     * 僚机升级所消耗的金币与能源所占比例 *
     */
    public static float JIN_BI_WING_UP = 0.1F;
    /**
     * 装备拆分时所获得的能源与升级所消耗能源的比例 *
     */
    public static float ZHUANG_BEI_CHAI_FEN = 0.1F;
    /**
     * 僚机被吞噬时所获得的经验与升级所消耗经验的比例 *
     */
    public static float LIAO_JI_TUN_SHI = 0.1F;
    /**
     * 装备升级时的总的参数。 *
     */
    public static float ZHUANG_BEI_SHENG_JI_CHANG_LIANG = 10F;
    /**
     * 经典模式核弹携带上限 *
     */
    public static int HEI_DAN_XIE_DAI_SHANG_XIAN = 9;

    public static void reload(){
        DefineTempletCfg.init();
        JIN_BI_EQUIPMENT_UP = getFloat( "JIN_BI_EQUIPMENT_UP" );
        JIN_BI_WING_UP = getFloat( "JIN_BI_WING_UP" );
        ZHUANG_BEI_CHAI_FEN = getFloat( "ZHUANG_BEI_CHAI_FEN" );
        LIAO_JI_TUN_SHI = getFloat( "LIAO_JI_TUN_SHI" );
        ZHUANG_BEI_SHENG_JI_CHANG_LIANG = getFloat( "ZHUANG_BEI_SHENG_JI_CHANG_LIANG" );
        HEI_DAN_XIE_DAI_SHANG_XIAN = getInt( "HEI_DAN_XIE_DAI_SHANG_XIAN" );
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

    private static double getDouble(String s) {
        return new Double(get(s));
		}
}