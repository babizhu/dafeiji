package com.hz.dafeiji.ai.user.modules.wing;

import com.hz.dafeiji.ai.user.User;
import com.hz.util.D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class WingModuleTest{

    String uname = D.TEST_USER_NAME;
    User user = new User( uname );
    private final WingModule module = user.getModuleManager().getWingModule();

    @Before
    public void setUp() throws Exception{

    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void testSetCurrentWing() throws Exception{

    }

    @Test
    public void testLevelUp() throws Exception{

    }

    @Test
    public void testSell() throws Exception{

    }

    @Test
    public void testBuy() throws Exception{

    }

    @Test
    public void testAdd() throws Exception{

    }

    @Test
    public void testGetAll() throws Exception{
        Map<Long, Wing> all = module.getAll();
        System.out.println( all );
    }

    @Test
    public void testQualityUp() throws Exception{

    }
}