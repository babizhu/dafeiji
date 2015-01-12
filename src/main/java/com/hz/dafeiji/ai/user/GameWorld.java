package com.hz.dafeiji.ai.user;

import com.bbz.tool.time.SystemTimer;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.modules.plane.Plane;
import com.hz.dafeiji.ai.user.modules.plane.PlaneModule;
import com.hz.dafeiji.ai.user.player.UserBaseInfo;
import com.hz.dafeiji.ai.user.player.UserBaseInfoManager;
import com.hz.util.D;

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
    private final UserBaseInfoManager userBaseInfoManager = new UserBaseInfoManager();

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

        if( user != null ) {
            ErrorCode eCode = user.getUserBaseInfo().login( pass );
            if( eCode != ErrorCode.SUCCESS ) {
                throw new ClientException( eCode );
            }
        } else {

//            ErrorCode eCode = userBaseInfoManager.login( uname, pass );
//            if( eCode != ErrorCode.SUCCESS ) {
//            }
//            user = getUserByName( uname );
            throw new ClientException( ErrorCode.USER_NOT_FOUND );

        }

        user.setSession( buildSession() );
        user.setLoginTime( SystemTimer.currentTimeSecond() );
        user.setActiveTimeNow();
        return user;

    }

    public ErrorCode regist( String uname, String pass ){

        //UserBaseInfoModule module = new UserBaseInfoModule( uname );
        ErrorCode eCode = userBaseInfoManager.regist( uname, uname + "nick", pass );
        if( eCode.isSuccess() ) {
            User user = getUserByName( uname );
            initNewUser( user );
        }
        return eCode;
    }

    /**
     * 玩家注册后紧接着的一些初始化操作，包括：
     * 1、生成一架飞机，并设置为出战
     *
     * @param user 用户
     */
    private void initNewUser( User user ){
        PlaneModule planeModule = user.getModuleManager().getPlaneModule();
        Plane plane = planeModule.create( D.DEFAULT_PLANE_ID );
        planeModule.setCurrentPlane( plane );


    }

    /**
     * 从内存或者数据库中获取玩家信息
     *
     * @param uname 玩家名
     * @return 玩家       如果玩家不存在，则返回null
     */
    User getUserByName( String uname ){

        User user = users.get( uname );
        if( user != null ) {
            return user;
        }

        UserBaseInfo userBaseInfo = userBaseInfoManager.getUserByName( uname );

        if( userBaseInfo != null ) {
            user = new User( userBaseInfo );
            users.put( uname, user );
            return user;
        }
        return null;
    }

    /**
     * 通过用户名获取玩家信息
     *
     * @param nickName 玩家昵称
     * @return 玩家       如果玩家不存在，则返回null
     */
    User getUserByNickName( String nickName ){
        UserBaseInfo info = userBaseInfoManager.getUserByNick( nickName );
        return getUserByName( info.getUserName() );
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
     * 通过玩家的用户名和sesseion，判断此玩家是否合法在线
     *
     * @param uname   玩家用户名
     * @param session 玩家session
     * @return 找到的玩家类，如果不存在或者sesseion错误，则返回null
     */
    public User getUserBySession( String uname, String session ){

        User user = users.get( uname );
        if( user == null ) {
            return null;
        }

        if( uname.equals( "bbz" ) ) {//方便调试用
            return user;
        }

        if( !user.getSesseion().equals( session ) ) {
            return null;
        }
        return user;
    }

}
