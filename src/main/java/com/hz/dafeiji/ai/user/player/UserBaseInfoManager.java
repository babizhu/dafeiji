package com.hz.dafeiji.ai.user.player;

import com.bbz.tool.identity.IdentityGen;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.UserStatus;

/**
 * user         LIUKUN
 * time         2014-12-19 15:00
 * <p/>
 * 管理所有玩家的基本信息，包括用户名，密码，主要用于登陆
 */

public class UserBaseInfoManager{

//    INSTANCE;

    private UserBaseInfoProvider db = new UserBaseInfoProvider();




    /**
     * 玩家注册
     *
     * @param uname 用户名
     * @param pass  密码
     * @return 结果码
     */
    public ErrorCode regist( String uname, String nickName, String pass ){

        if( db.userIsDuplicate( uname, nickName ) ) {
            return ErrorCode.USER_DUPLICATE;
        }

        UserBaseInfo info = new UserBaseInfo();
        info.setUid( IdentityGen.INSTANCE.incrementAndGet() );
        info.setUserStatus( UserStatus.SUCCESS );
        info.setPass( pass );
        info.setNickName( nickName );
        info.setUserName( uname );
        db.save( info );

//
        return ErrorCode.SUCCESS;
    }


//    public ErrorCode login( String uname, String pass ){
//        UserBaseInfo userBaseInfo = db.findByName( uname );
//
//        if( userBaseInfo == null ) {
//            return ErrorCode.USER_NOT_FOUND;
//        }
//
//        return userBaseInfo.login( pass );
//    }


//
//    public String getNickName(){
//        return info.getNickName();
//    }


    /**
     * 删除玩家
     * tester用，其他谨慎调用
     */
    public void removeUser( String userName ){
        db.remove( userName );
    }

    /**
     * 根据用户名获取玩家的UserBaseInfo信息
     * @param userName  用户名
     * @return 玩家的基础信息
     */
    public UserBaseInfo getUserByName( String userName ){
        return db.findByName( userName );
    }

    public UserBaseInfo getUserByNick( String nickName ){
        return db.findByNick( nickName );
    }

}
