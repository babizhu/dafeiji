package com.hz.dafeiji.ai.user.modules.misc.usercounter;

import com.hz.dafeiji.ai.user.ModuleManager;
import com.hz.dafeiji.ai.user.modules.misc.MiscDataKey;
import com.hz.util.D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserCounterModuleTest{
    String uname = D.TEST_USER_NAME;
    private ModuleManager manager = new ModuleManager(uname );
    private final UserCounterModule module = new UserCounterModule( uname );

    @Before
    public void setUp() throws Exception{
    }

    /**
     * 清空测试数据
     */
    @After
    public void clear(){
        module.clear();
    }


    @Test
    public void testGet() throws Exception{

        //1、尝试获取未初始化的计数器
        int i = module.get( MiscDataKey.ENEMY );
        assertEquals( i, 0 );


        //2、尝试获取未初始化的计数器（带参数）
        i = module.get( MiscDataKey.ENEMY, "a" );
        assertEquals( i, 0 );


        //写入一些数据
        module.put( MiscDataKey.ENEMY, 3 );

        //3、正常获取数据
        i = module.get( MiscDataKey.ENEMY );
        assertEquals( i, 3 );

        //4、正常获取数据（带参数）
        module.put( MiscDataKey.ENEMY, 30, "100" );
        i = module.get( MiscDataKey.ENEMY, "100" );
        assertEquals( i, 30 );

        //此处打断点调试系统时间到明天，再取数值应该变成0，系统打包时候应该屏蔽掉
//        i = module.get( MiscDataKey.ENEMY, "100" );
//        assertEquals( i, 0 );


    }

    @Test
    public void testPut() throws Exception{

    }

    @Test
    public void testIsMark() throws Exception{

    }

    @Test
    public void testAdd() throws Exception{
        //1、未初始化直接加
        int i1 = module.add( MiscDataKey.ENEMY, 4 );
        int i = module.get( MiscDataKey.ENEMY );
        assertEquals( i, 4 );
        assertEquals( i1, 4 );

        //2、正常加
        i1 = module.add( MiscDataKey.ENEMY, 6 );
        i = module.get( MiscDataKey.ENEMY );
        assertEquals( i, 10 );
        assertEquals( i1, 10 );

        //3、带上参数重复上面步骤
        i1 = module.add( MiscDataKey.MONEY_TREE, 300000, 1 + "", 2 + "" );
        i = module.get( MiscDataKey.MONEY_TREE, 1 + "", 2 + "" );
        assertEquals( i, 300000 );
        assertEquals( i1, 300000 );
    }

    @Test
    public void testClear() throws Exception{
        //1、尝试获取未初始化的计数器
        boolean b = module.isMark( MiscDataKey.ENEMY );
        assertEquals( b, false );


        //2、尝试获取未初始化的计数器（带参数）
        b = module.isMark( MiscDataKey.ENEMY, "a" );
        assertEquals( b, false );


        //写入一些数据
        module.setMark( MiscDataKey.ENEMY, true );

        //3、正常获取数据
        b = module.isMark( MiscDataKey.ENEMY );
        assertEquals( b, true );

        //4、正常获取数据（带参数）
        module.setMark( MiscDataKey.ENEMY, true, "100" );
        b = module.isMark( MiscDataKey.ENEMY, "100" );
        assertEquals( b, true );

        //5、测试false的情况
        module.setMark( MiscDataKey.ENEMY, false, "100" );
        b = module.isMark( MiscDataKey.ENEMY, "100" );
        assertEquals( b, false );
    }

    @Test
    public void testToString() throws Exception{
        module.put( MiscDataKey.ENEMY, 30, "100" );
        module.put( MiscDataKey.ENEMY, 30 );
        module.put( MiscDataKey.ENEMY, 300000 );
        module.put( MiscDataKey.MONEY_TREE, 300000, 1 + "", 2 + "" );
        System.out.println( module.toString() );
    }
}