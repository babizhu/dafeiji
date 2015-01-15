package com.hz.dafeiji.ai.user.modules.stuff;

import com.hz.dafeiji.ai.user.User;
import com.hz.util.D;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;


public class StuffModuleTest{

    String uname = D.TEST_USER_NAME;
    User user = new User( uname );
    //    private ModuleManager manager = new ModuleManager( uname );
    private final StuffModule module = user.getModuleManager().getStuffModule();

    /*
    * 每个测试用例之后清空测试数据
    */
    @Before
    public void clear(){
        //System.out.println( "--------------------------------------------------");
        module.removeAll();
    }
    @Test
    public void testChangeStuff() throws Exception{
        module.changeStuff( 12345,10 );
        module.changeStuff( 12345,10 );
        module.changeStuff( 12335,10 );

        assertEquals( 2, module.getAllStuff().size() );
    }




    @Test
    public void testIsEnough() throws Exception{

    }

    @Test
    public void testGetCount() throws Exception{
        int propId = 938493;
        module.getCount( propId );
        assertEquals( 0, module.getCount( propId ) );
        module.changeStuff( propId,4 );
        assertEquals( 4, module.getCount( propId ) );
    }

    @Test
    public void testGetAllStuff() throws Exception{

    }
}