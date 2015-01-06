package com.hz.dafeiji.ai.user.modules.player;

import com.bbz.tool.identity.IdentityGen;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.UserStatus;

/**
 * user         LIUKUN
 * time         2014-12-19 15:00
 * <p/>
 * 玩家的基本信息，包括用户名，密码，主要用于登陆
 */

public class UserBaseInfoModule{

    private UserBaseInfoProvider db;
    private UserBaseInfo info;
    private String userName;

    public UserBaseInfoModule( String userName ){

        this.userName = userName;
        db = new UserBaseInfoProvider( userName );

        info = db.findOne();
    }


    /**
     * 玩家注册
     *
     * @param uname 用户名
     * @param pass  密码
     * @return 结果码
     */
    public ErrorCode regist( String uname, String nickName, String pass ){
        if( info != null ) {//此用户名已经存在
            return ErrorCode.USER_DUPLICATE;
        }

        if( db.userIsDuplicate( nickName ) ) {

            return ErrorCode.USER_DUPLICATE;
        }
        info = new UserBaseInfo();
        info.setUid( IdentityGen.INSTANCE.incrementAndGet() );
        info.setUserStatus( UserStatus.SUCCESS );
        info.setPass( pass );
        info.setNickName( nickName );
        info.setUserName( uname );
        //System.out.println( info );
        db.replace( info );


        return ErrorCode.SUCCESS;
    }


    public ErrorCode login( String pass ){
        if( info.getPass() != null && info.getPass().equals( pass ) ) {
            return ErrorCode.SUCCESS;
        }
        return ErrorCode.USER_UNAME_PASS_INVALID;
    }

    public UserBaseInfo getInfo(){

        return info;
    }

    public String getNickName(){
        return info.getNickName();
    }

    /**
     * 此用户是否存在DB中
     *
     * @return true 存在     false   不存在
     */
    public boolean existInDB(){
        return info != null;
    }

    /**
     * 删除玩家
     * tester用，其他谨慎调用
     */
    public void removeUser(){
        db.remove();
    }

    public String getUserName(){
        return userName;
    }
}
