package com.hz.dafeiji.ai.user.modules.misc;

import com.hz.dafeiji.ai.user.User;
import com.hz.util.D;
import org.junit.Test;


public class MiscDataModuleTest{

    private String uname = D.TEST_USER_NAME;

    User user = new User( uname );
    MiscDataModule module = user.getModuleManager().MiscDataModule();
    //PropertyModule propertyModule = user.getModuleManager().getPropertyModule();

    @Test
    public void testGetString() throws Exception{

        module.put( MiscDataKey.MONEY_TREE, "abcd");
        String result = module.getString( MiscDataKey.MONEY_TREE );
        System.out.println( result);

        module.put( MiscDataKey.MOPPING_UP, 1,"1");
        module.put( MiscDataKey.MOPPING_UP, 0,"2");
        module.put( MiscDataKey.MOPPING_UP, 3,"3");

        int i = module.getInt( MiscDataKey.MOPPING_UP, "2" );

        System.out.println( i );

    }

    @Test
    public void testGetInt() throws Exception{

    }

    @Test
    public void testPut() throws Exception{

    }

    @Test
    public void testClear() throws Exception{

    }
}