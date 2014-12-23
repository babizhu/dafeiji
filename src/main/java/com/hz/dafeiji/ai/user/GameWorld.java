package com.hz.dafeiji.ai.user;

import com.bbz.tool.time.SystemTimer;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * user         LIUKUN
 * time         2014-12-19 10:34
 * <p/>
 * 总的游戏管理类
 */

public enum GameWorld{

    INSTANCE;
    //private

    /**
     * 所有内存中的用户，不一定是在线的,session 用户信息
     */
    private Map<String, User> users = new HashMap<>();

    /**
     * 获取玩家的唯一验证码
     *
     * @return
     */
    String buildSession(){
        return UUID.randomUUID().toString();
    }


    public User login( String uname, String pass ){

        User user = getUserByName( uname );
        ErrorCode eCode = user.getModuleManager().getUserBaseInfoModule().login( pass );
        if( eCode == ErrorCode.SUCCESS ) {
            String session = buildSession();
            users.put( session, user );
            user.setSession( session );
            user.setLoginTime( SystemTimer.currentTimeSecond() );
            user.setActiveTimeNow();
            return user;
        } else {
            throw new ClientException( eCode );
        }
    }

    public ErrorCode regist( String uname, String pass ){
        User user = getUserByName( uname );
        return user.getModuleManager().getUserBaseInfoModule().regist( uname, uname + "nick", pass );
    }

    /**
     * 从内存或者数据库中获取玩家信息
     *
     * @param uname
     * @return
     */
    User getUserByName( String uname ){
        User user = new User( uname );
        return user;
    }

    User getUserByNickName( String uname ){
        return null;
    }


    public User getUserBySession( String session ){
        return users.get( session );
    }


}
