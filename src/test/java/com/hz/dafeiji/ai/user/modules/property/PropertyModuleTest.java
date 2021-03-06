package com.hz.dafeiji.ai.user.modules.property;

import com.hz.dafeiji.ai.user.modules.ModuleManager;
import com.hz.util.D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PropertyModuleTest{
    String uname = D.TEST_USER_NAME;
    private ModuleManager manager = new ModuleManager( uname );
    private final PropertyModule module = new PropertyModule( uname );


    /**
     * 清空测试数据
     */
    @Before
    public void clear(){
        module.removeUserData();
    }

    @Test
    public void testReduceStrength() throws Exception{

        //1、数据库尚无玩家信息时候

        assertEquals( module.getStengthMax(), module.getStrength() );

        int remainStrength = module.changeStrength( -2 );
        assertEquals( module.getStengthMax() - 2, module.getStrength() );
        assertEquals( remainStrength, module.getStrength() );


    }
}