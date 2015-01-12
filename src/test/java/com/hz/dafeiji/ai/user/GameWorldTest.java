package com.hz.dafeiji.ai.user;

import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.player.UserBaseInfoManager;
import com.hz.dafeiji.cfg.plane.PlaneTempletCfg;
import com.hz.util.D;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class GameWorldTest{

    private static String uname = D.TEST_USER_NAME;

    @BeforeClass
    public static void init(){
        PlaneTempletCfg.init();
//        CfgInit.init();


    }

    @Before
    public void removeUser(){

    }

    @Test
    public void testRemoveUser() throws Exception{
        String uname = "bbz";
        removeUser( uname );
    }

    /**
     * 删除玩家bbz的相关信息，方便测试
     *
     * @throws Exception
     */

    public void removeUser( String uname ) throws Exception{

        UserBaseInfoManager module = new UserBaseInfoManager();
        User user = new User( uname );
        user.remove();
        module.removeUser( uname );
    }

    /**
     * 这是性能测试，一般情况不开启执行(注释掉@Test)
     * 注册用户并初始化1架飞机的情况，注册并登陆10w用户，耗时约64~67秒
     *
     * @throws Exception
     */

    @Test
    public void testPerformance() throws Exception{
        int count = 100000;
        UserBaseInfoManager module = new UserBaseInfoManager();
        for( int i = 0; i < count; i++ ) {
            User user = new User( uname + i );
            user.remove();
            module.removeUser( uname + i );

        }

        long begin = System.nanoTime();
        for( int i = 0; i < count; i++ ) {
            ErrorCode code = GameWorld.INSTANCE.regist( uname + i, "pass" );
            if( code != ErrorCode.SUCCESS ) {
                System.out.println( code );
                return;
            }
            User loginUser = GameWorld.INSTANCE.login( uname + i, "pass" );
            if( i % 1000 == 0 ) {
//                System.out.println( uname + i + "[" + loginUser.getUserBaseInfo().getNickName() + "]注册并登陆完毕" );
                System.out.println( uname + i + "[" + "]注册并登陆完毕" );

            }
        }
        System.out.println( "操作耗时：" + (System.nanoTime() - begin) / 1000000000f + "秒" );
        System.out.println( "当前在线人数：" + GameWorld.INSTANCE.getOnlineUser() );
    }

    @Test
    public void testRegist() throws Exception{
        //String name = "xtestUserbbz";
        removeUser( uname );
        ErrorCode code = GameWorld.INSTANCE.regist( uname, "pass" );
        assertEquals( ErrorCode.SUCCESS, code );
        code = GameWorld.INSTANCE.regist( uname, "pass" );
        assertEquals( ErrorCode.USER_DUPLICATE, code );

        removeUser( uname );
    }

    @Test
    public void testGetUserByNickName() throws Exception{

    }

    @Test
    public void testGetUserBySession() throws Exception{

    }
}