package com.hz.dafeiji.ai.user.modules.plane;

import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.cfg.plane.PlaneTempletCfg;
import com.hz.util.D;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class PlaneModuleTest{


    String uname = D.TEST_USER_NAME;
    //    private ModuleManager manager = new ModuleManager( uname );
    private final PlaneModule module = new PlaneModule( uname );

    /**
     * 初始化配置表
     */
    @BeforeClass
    public static void init(){
        PlaneTempletCfg.init();

    }

    /*
    * 每个测试用例之后清空测试数据
    */
    @After
    public void clear(){
        //System.out.println( "--------------------------------------------------");
        module.remove();
    }

    @Test
    /**
     * 测试新玩家尚未初始化时候的的情况
     */
    public void testGetAll() throws Exception{

        Map<Long, Plane> all = module.getAll();
        assertEquals( 0, all.size() );


    }

    private void printAll( Map<Long, Plane> planes ){
        for( Map.Entry<Long, Plane> entry : planes.entrySet() ) {
            System.out.println( "key=" + entry.getKey() + "|value=" + entry.getValue() );

        }
    }

    @Test
    public void testLevelUp() throws Exception{

    }


    @Test
    public void testBuy() throws Exception{
        module.buy( 100501 );
        assertEquals( 1, module.getAll().size() );

        module.buy( 100401 );
        module.buy( 100301 );
        module.buy( 100201 );
        module.buy( 100101 );
        //testGetAll();

        assertEquals( 5, module.getAll().size() );

//        printAll( module.getAll() );

        try {
            //测试重复模板id
            module.buy( 100101 );
        } catch( ClientException e ) {
            assertEquals( e.getCode(), ErrorCode.PLANE_TEMPLET_DUPLICATE );
        }

        try {
            //测试无效模板id
            module.buy( -100101 );
        } catch( ClientException e ) {
            assertEquals( e.getCode(), ErrorCode.PLANE_TEMPLET_NOT_FOUND );
        }

        //module.db.add( new Plane( (long) 1, PlaneTempletCfg.getPlaneTempletById( 100201 ) ) );

    }

    @Test
    public void testGetCurrentPlane(){
        assertEquals( 0, module.getAll().size() );
        Plane plane = module.buy( D.DEFAULT_PLANE_ID );
        module.setCurrentPlane( plane );
        assertEquals( true, plane.isCurrent() );

        assertEquals( D.DEFAULT_PLANE_ID, module.getCurrentPlane().getTemplet().getId() );
        Plane newPlane = module.buy( 100201 );
        module.setCurrentPlane( newPlane );

        assertEquals( 100201, module.getCurrentPlane().getTemplet().getId() );
        assertEquals( false, plane.isCurrent() );
        assertEquals( true, newPlane.isCurrent() );


    }

    @Test
    public void testSetCurrentPlane(){

    }
}
