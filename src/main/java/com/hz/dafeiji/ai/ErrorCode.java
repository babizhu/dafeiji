package com.hz.dafeiji.ai;

import java.util.HashMap;
import java.util.Map;

/**
 * user         LIUKUN
 * time         14-3-26 下午7:50
 */
public enum ErrorCode{

    SUCCESS( 0 ),

    /**
     * 无效的http请求
     */
    HTTP_INVALID_REQUEST( 100 ),

    /**
     * 处理句柄不存在
     */
    HANDLER_NOT_FOUND( 101 ),

    /**
     * 签名验证错误
     */
    SIGNATURE_ERROR( 102 ),
    /**
     * 尚未登录
     */
    USER_NOT_LOGIN( 1000 ),
    /**
     * 用户名或昵称重复
     */
    USER_DUPLICATE( 1001 ),
    /**
     * 已经登录
     */
    USER_HAS_LOGIN( 1002 ),
    /**
     * 用户名或密码错误
     */
    USER_UNAME_PASS_INVALID( 1003 ),
    /**
     * 用户体力值不足
     */
    USER_STRENGTH_NOT_ENOUGH( 1004 ),
    HERO_NOT_FOUND( 2000 ),
    EQUPMENT_NOT_FOUND( 3000 ),
    /**
     * 摇钱树今日运行次数超上限
     */
    MONEY_TREE_RUN_UPPER_LIMIT( 4000 );

    private static final Map<Integer, ErrorCode> numToEnum = new HashMap<>();

    static{
        for( ErrorCode t : values() ) {

            ErrorCode s = numToEnum.put( t.number, t );
            if( s != null ) {
                throw new RuntimeException( t.number + "重复了" );
            }
        }
    }

    private final int number;
    private final String msg;

    ErrorCode( int number ){
        this.number = number;
        this.msg = "aaa";
    }

    ErrorCode( int number, String msg ){
        this.number = number;
        this.msg = msg;
    }

    public static ErrorCode fromNum( int n ){
        return numToEnum.get( n );
    }

    public int toNum(){
        return number;
    }

}
