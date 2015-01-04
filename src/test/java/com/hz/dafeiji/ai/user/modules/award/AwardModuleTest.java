package com.hz.dafeiji.ai.user.modules.award;

import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.property.PropertyModule;
import com.hz.util.D;
import org.junit.After;
import org.junit.Test;

public class AwardModuleTest{
    private String uname = D.TEST_USER_NAME;
    User user = new User( uname );
    AwardModule module = user.getModuleManager().getAwardModule();
    PropertyModule propertyModule = user.getModuleManager().getPropertyModule();

    /**
     * 清空测试数据
     */
    @After
    public void clear(){
        propertyModule.remove();
    }

    @Test
    public void testAddAward() throws Exception{


        System.out.println( propertyModule );

        module.addAward( "500001,1000", "test" );
        System.out.println( propertyModule );

    }

    @Test
    public void testReduceAward() throws Exception{

    }
}