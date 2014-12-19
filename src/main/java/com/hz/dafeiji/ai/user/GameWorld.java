package com.hz.dafeiji.ai.user;

import com.hz.dafeiji.ai.ErrorCode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * user         LIUKUN
 * time         2014-12-19 10:34
 *
 * 总的游戏管理类
 */

public enum  GameWorld{

    INSTANCE;
    //private

    /**
     * 所有内存中的用户，不一定是在线的,uname 用户信息
     */
    private Map<String,User> users = new HashMap<>(  );
    /**
     * 获取玩家的唯一验证码
     * @return
     */
    String buildSession(){
        return UUID.randomUUID().toString();
    }



    ErrorCode login( String uname, String pass ){

        User user = getUserByName( uname );
        users.put( buildSession(), user );
        return ErrorCode.SUCCESS;
    }

    ErrorCode regist( String uname, String pass ){
        User user = new User( uname );
        return user.getModuleManager().getUserLoginModule().regist( uname, uname + "nick",pass );
    }

    /**
     * 从内存或者数据库中获取玩家信息
     * @param uname
     * @return
     */
    User getUserByName(String uname ){
        return null;
    }

    User getUserByNickName(String uname ){
        return null;
    }


    User getUserBySession(String session){
        return users.get( session );
    }

    public static void main( String[] args ){

        long begin = System.nanoTime();
        for( int i = 0; i < 10000; i++ ) {
            ErrorCode code = GameWorld.INSTANCE.regist( "abcde" + i, "pass");
            if( code != ErrorCode.SUCCESS ){
                System.out.println( code );
            }
        }
        System.out.println( "操作耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒" );

    }


}
