package com.hz.dafeiji.ai.user;

import com.bbz.tool.time.SystemTimer;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.modules.plane.Plane;
import com.hz.dafeiji.ai.user.modules.plane.PlaneModule;
import com.hz.dafeiji.ai.user.modules.player.UserBaseInfoModule;
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


            return user;
        } else {
            throw new ClientException( eCode );
        }
    }

    public ErrorCode regist( String uname, String pass ){
        //这个地方怎么处理呢，难道这里的玩家也放在users里面，明显有问题
        //好像解决了，明天在测试把
        UserBaseInfoModule module = new UserBaseInfoModule( uname );

        ErrorCode eCode = module.regist( uname, uname + "nick", pass );

        if( eCode == ErrorCode.SUCCESS ) {
            User user = getUserByName( uname );
            initNewUser( user );
        }
        return eCode;


    }

    /**
     * 玩家注册后紧接着的一些初始化操作，包括：
     * 1、生成一架飞机，并设置未出战
     *
     * @param user      用户
     */
    private void initNewUser( User user ){
        PlaneModule planeModule = user.getModuleManager().getPlaneModule();
        Plane plane = planeModule.buy( D.DEFAULT_PLANE_ID );
        planeModule.setCurrentPlane( plane );


    }

    /**
     * 从内存或者数据库中获取玩家信息
     *
     * @param uname 玩家名
     * @return 玩家
     */
    User getUserByName( String uname ){
        User user = users.get( uname );
        if( user != null ) {
            return user;
        }
        user = new User( uname );
        users.put( uname, user );
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

//    public User getUserBySession( String session ){
//        return users.get( session );
//    }


}
