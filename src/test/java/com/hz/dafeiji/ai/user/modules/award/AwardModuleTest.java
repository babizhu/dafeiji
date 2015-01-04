package com.hz.dafeiji.ai.user.modules.award;

import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.property.PropertyModule;
import com.hz.util.D;
import org.junit.Test;

public class AwardModuleTest{

    private String uname = D.TEST_USER_NAME;
    @Test
    public void testAddAward() throws Exception{
        User user = new User( uname );
        AwardModule module = user.getModuleManager().getAwardModule();
        PropertyModule propertyModule = user.getModuleManager().getPropertyModule();
        //module.
    }

    @Test
    public void testReduceAward() throws Exception{

    }
}