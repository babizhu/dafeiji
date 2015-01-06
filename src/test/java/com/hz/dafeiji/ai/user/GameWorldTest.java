package com.hz.dafeiji.ai.user;

import com.hz.util.D;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameWorldTest{

    private static String uname = D.TEST_USER_NAME;

    @BeforeClass
    public static void init(){
        //PlaneTempletCfg.init();
//        CfgInit.init();
    }

    @AfterClass
    public static void removeUser(){
        User user = new User( uname );
        user.getUserBaseInfoModule().removeUser();
    }

    @Test
    public void testBuildSession() throws Exception{

    }

    @Test
    public void testRegistAndLogin() throws Exception{
//        ErrorCode code = GameWorld.INSTANCE.regist( uname, "pass" );
//        if( code != ErrorCode.SUCCESS ) {
//            System.out.println( code );
//        }
//        User loginUser = GameWorld.INSTANCE.login( uname, "pass" );
//        long begin = System.nanoTime();
//        for( int i = 0; i < 10; i++ ) {
//            ErrorCode code = GameWorld.INSTANCE.regist( "eabdcdeqfd" + i, "pass" );
//            if( code != ErrorCode.SUCCESS ) {
//                System.out.println( code );
//            }
//        }
//        System.out.println( "操作耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒" );
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