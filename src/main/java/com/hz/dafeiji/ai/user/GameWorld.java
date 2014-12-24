package com.hz.dafeiji.ai.user;

import com.bbz.tool.time.SystemTimer;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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
     * 所有内存中的用户，不一定是在线的,uname, 用户信息
     */
    private Map<String, User> users = new ConcurrentHashMap<>();

    /**
     * 获取玩家的唯一验证码
     *
     * @return session
     */
    String buildSession(){
        return UUID.randomUUID().toString();
    }


    public User login( String uname, String pass ){

        User user = getUserByName( uname );
        ErrorCode eCode = user.getModuleManager().getUserBaseInfoModule().login( pass );
        if( eCode == ErrorCode.SUCCESS ) {
            String session = buildSession();

            user.setSession( session );
            user.setLoginTime( SystemTimer.currentTimeSecond() );
            user.setActiveTimeNow();

            users.put( uname, user );
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
     * @param uname     玩家名
     * @return 玩家
     */
    User getUserByName( String uname ){
        User user = users.get( uname );
        if( user != null ) {
            return user;
        }
        user = new User( uname );
        return user;
    }

    User getUserByNickName( String uname ){
        return null;
    }

    public int getOnlineUser(){
        int count = 0;
        for( User user : users.values() ) {
            if( user.isOnline() ) {

                count++;
            }
        }
        return count;
    }

    /**
     * 通过玩家的用户名和sesseion，判断此
     *
     * @param uname
     * @param session
     * @return
     */
    public User getUserBySession( String uname, String session ){

        User user = users.get( uname );
        if( user == null ) {
            return null;
        }

        if( !user.getSesseion().equals( session ) ) {
            return null;
        }
        return user;
    }

//    public User getUserBySession( String session ){
//        return users.get( session );
//    }


}
