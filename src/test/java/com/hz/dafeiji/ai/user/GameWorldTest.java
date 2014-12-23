package com.hz.dafeiji.ai.user;

import com.hz.dafeiji.ai.ErrorCode;
import org.junit.Test;

public class GameWorldTest{

    @Test
    public void testBuildSession() throws Exception{

    }

    @Test
    public void testLogin() throws Exception{
        int count = 10;
        for( int i = 0; i < count; i++ ) {
            String uname = "eabdcdeqfd" + i;
            User loginUser = GameWorld.INSTANCE.login( uname, "papss" );
            System.out.println( loginUser + "登陆" );

        }

    }

    @Test
    public void testRegist() throws Exception{
        long begin = System.nanoTime();
        for( int i = 0; i < 10; i++ ) {
            ErrorCode code = GameWorld.INSTANCE.regist( "eabdcdeqfd" + i, "pass" );
            if( code != ErrorCode.SUCCESS ) {
                System.out.println( code );
            }
        }
        System.out.println( "操作耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒" );
    }

    @Test
    public void testGetUserByName() throws Exception{

    }

    @Test
    public void testGetUserByNickName() throws Exception{

    }

    @Test
    public void testGetUserBySession() throws Exception{

    }
}