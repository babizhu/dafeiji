package com.hz.dafeiji.cfg.manual;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import com.hz.dafeiji.cfg.wing.WingQurlityTemplet;
import com.hz.dafeiji.cfg.wing.WingQurlityTempletCfg;

import java.util.Map;

/**
 * user         LIUKUN
 * time         2015-1-7 15:32
 * 根据品阶和等级获取所需经验
 * 未降低策划工作量，手动生产经验表
 * <p/>
 * 1级 exper=0       1级的经验必须为0
 * 2级 exper=20
 * 3级 exper=30
 * 4级 exper=40
 * 5级 exper=50
 * 6级 exper=60
 * 7级 exper=70
 * getMaxLevel( 1, 0,69 )   maxLevel=6
 */

public class WingExpCfg{
    //品阶，等级，总经验
    private static final Table<Integer, Integer, Integer> table = TreeBasedTable.create();//HashBasedTable.create();

    public static final int MAX_QUALITY = 1000;

    static{


        init();

        //WingQurlityTempletCfg.


    }

    public static void init(){
        for( int i = 1; i < MAX_QUALITY; i++ ) {
            WingQurlityTemplet wt = WingQurlityTempletCfg.getWingQurlityTempletById( i );
            if( wt != null ) {
                int expInit = wt.getExpInitial();
                int expGap = wt.getExpGap();
                for( int j = 1; j <= wt.getMaxLv(); j++ ) {
                    //总经验=初始经验*（等级-1）+（等级-1）*（等级-2）/2*单级经验差
                    int needExp = expInit * (j - 1) + (j - 1) * (j - 2) / 2 * expGap;
                    table.put( i, j, needExp );
                }
            } else {
                break;
            }
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static void printAll(){
        for( int i = 1; i < MAX_QUALITY; i++ ) {

            Map<Integer, Integer> row = table.row( i );
            if( !row.isEmpty() ) {
                WingQurlityTemplet wt = WingQurlityTempletCfg.getWingQurlityTempletById( i );
                for( int j = 1; j <= wt.getMaxLv(); j++ ) {
                    System.out.print( i + "," + j + "=" );
                    System.out.println( row.get( j ) );
                }

            } else {
                break;
            }
        }
    }

    public static int getExp( int quality, int level ){


        return table.get( quality, level );
    }

    /**
     * 根据品阶和当前经验以及要增加的经验，计算可以升级的最大等级
     * @param quality    品阶
     * @param currentExp 当前经验
     * @param expAdd     要增加的经验
     * @return 可以升级的最大等级
     */
    public static int getMaxLevel( int quality, int currentExp, int expAdd ){

        int expTemp = currentExp + expAdd;
        Map<Integer, Integer> row = table.row( quality );


        for( Map.Entry<Integer, Integer> entry : row.entrySet() ) {
            if( entry.getValue() > expTemp ) {
                return entry.getKey() - 1;
            }

        }
        return WingQurlityTempletCfg.getWingQurlityTempletById( quality ).getMaxLv();
    }

    public static void main( String[] args ){

        WingQurlityTempletCfg.init();
        init();
        System.out.println( getExp( 2, 10 ) );
        System.out.println( "maxLevel=" + getMaxLevel( 1, 1, 6900000 ) );
        float f = 0.01F;
        int s = 0;
        s += 301*f;
        s += 301*f;
        System.out.println( s);
//        printAll();
    }
}
