package com.hz.dafeiji.ai.user.modules.misc;

import com.google.common.base.Joiner;

import java.util.HashMap;
import java.util.Map;

/**
 * user         LIUKUN
 * time         2014-5-3 11:41
 * <p/>
 * 为了不引起混淆，枚举中不要包含用于生成key的"^",因为系统采用"^"作为分隔符
 */
public enum MiscDataKey{

    ENEMY( 1 ), /**
     * 关卡的扫荡次数
     */
    MOPPING_UP( 2 ),

    /**
     * 摇钱树
     */
    MONEY_TREE( 3 ),
    /**
     * 最大闯关数
     */
    MAX_MISSION_ID( 4 );
    private static final Map<Integer, MiscDataKey> numToEnum = new HashMap<>();

    static{
        for( MiscDataKey t : values() ) {

            MiscDataKey s = numToEnum.put( t.number, t );
            if( s != null ) {
                throw new RuntimeException( t.number + "重复了" );
            }
        }
    }

    private final int number;

    MiscDataKey( int number ){
        this.number = number;
    }

    public static MiscDataKey fromNum( int n ){
        return numToEnum.get( n );
    }

    /**
     * 生成唯一的key，注意args中不能存在^符号，有可能导致程序出错
     * @param args
     * @return
     */
    public String buildKey( String[] args ){
        String ret = toString() + "^"; //^为分隔符
        //String ret = number + "^"; //^为分隔符，调试阶段就用字符串替代数字
        // 如果不加^分隔符，两个key分别为a和a1，a带个参数1，a1不带参数，则生成的key相同
        ret += Joiner.on( "^" ).join( args );
        return ret;
    }

    public int toNum(){
        return number;
    }

}
