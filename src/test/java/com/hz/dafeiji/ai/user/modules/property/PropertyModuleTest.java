package com.hz.dafeiji.ai.user.modules.property;

import com.hz.dafeiji.ai.user.ModuleManager;
import com.hz.util.D;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PropertyModuleTest{
    String uname = D.TEST_USER_NAME;
    private ModuleManager manager = new ModuleManager( uname );
    private final PropertyModule module = new PropertyModule( uname );


    /**
     * 清空测试数据
     */
    @After
    public void clear(){
        module.remove();
    }

    @Test
    public void testReduceStrength() throws Exception{
        //1、数据库尚无玩家信息时候
        assertEquals( 0, module.getLastCalcStrengthSecond() );
        assertEquals( module.getStengthMax(), module.getRealStrength() );

        int remainStrength = module.reduceStrength( 2 );
        assertEquals( module.getStengthMax() - 2, module.getRealStrength() );
        assertEquals( remainStrength, module.getRealStrength() );


    }
}