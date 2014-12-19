package com.hz.dafeiji.ai.user.modules.player;

import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.UserStatus;

/**
 * user         LIUKUN
 * time         2014-12-19 15:00
 *
 * 玩家的基本信息，包括用户名，密码，主要用于登陆
 */

public class UserBaseInfoModule{

    private UserBaseInfoProvider db;
    private UserBaseInfo info;

    public UserBaseInfoModule( String uname ){


        db = new UserBaseInfoProvider( uname );

        info = db.findOne( );
    }



    /**
     * 玩家注册
     * @param uname     用户名
     * @param pass      密码
     * @return          结果码
     */
    public ErrorCode regist( String uname, String nickName, String pass ){
        if( info.getUserName() != null ){

            return ErrorCode.USER_DUPLICATE;
        }

        if( db.isDuplicate( nickName ) ){
            System.out.println(2222);
            return ErrorCode.USER_DUPLICATE;
        }

        info.setUid( 23 );
        info.setUserStatus( UserStatus.SUCCESS );
        info.setPass( pass );
        info.setNickName( nickName );
        info.setUserName( uname );
        //System.out.println( info );
        db.replace( info );


        return ErrorCode.SUCCESS;
    }

    public static void main( String[] args ){
        UserBaseInfoModule module = new UserBaseInfoModule( "bbz" );
        System.out.println(module.regist( "bbz1","bbz1","pass" ));
    }

}
