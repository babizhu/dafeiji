package com.hz.dafeiji.ai.user.modules.plane;

import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.cfg.plane.PlaneTempletCfg;
import com.hz.util.D;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static com.hz.dafeiji.cfg.plane.PlaneTempletCfg.getPlaneTempletById;
import static junit.framework.Assert.assertEquals;

public class PlaneModuleTest{


    String uname = D.TEST_USER_NAME;
    User user = new User( uname );
    //    private ModuleManager manager = new ModuleManager( uname );
    private final PlaneModule module = user.getModuleManager().getPlaneModule();

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
    @Before
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

//    private void printAll( Map<Long, Plane> planes ){
//        for( Map.Entry<Long, Plane> entry : planes.entrySet() ) {
//            System.out.println( "key=" + entry.getKey() + "|value=" + entry.getValue() );
//
//        }
//    }

    @Test
    public void testLevelUp() throws Exception{

    }


    @Test
    public void testGetCurrentPlane(){
        assertEquals( 0, module.getAll().size() );
        Plane plane = module.buy( D.DEFAULT_PLANE_ID );
        module.setCurrentPlane( plane );
        assertEquals( true, plane.isCurrent() );

        assertEquals( D.DEFAULT_PLANE_ID, module.getCurrentPlane().getTemplet().getId() );

        //给点钱，方便扣除
        int planeTempletId = 100201;
        user.getModuleManager().getAwardModule().addAward( getPlaneTempletById( planeTempletId ).getPrice(), "test" );
        Plane newPlane = module.buy( planeTempletId );
        module.setCurrentPlane( newPlane );

        assertEquals( planeTempletId, module.getCurrentPlane().getTemplet().getId() );
        assertEquals( false, plane.isCurrent() );
        assertEquals( true, newPlane.isCurrent() );


    }

    @Test
    public void testBuy(){

        //module.remove();
        //购买price=0的飞机
        module.buy( D.DEFAULT_PLANE_ID );

        //测试重复购买
        try {
            module.buy( D.DEFAULT_PLANE_ID );
        } catch( ClientException e ) {
            assertEquals( ErrorCode.PLANE_TEMPLET_DUPLICATE, e.getCode() );
        }

        //测试无效模板id
        try {
            module.buy( 34242332 );
        } catch( ClientException e ) {
            assertEquals( ErrorCode.PLANE_TEMPLET_NOT_FOUND, e.getCode() );
        }

        int planeTempletId = 100201;
        //测试钱不够，无法购买的情况
        try {
            module.buy( planeTempletId );
        } catch( ClientException e ) {
            assertEquals( ErrorCode.AWARD_NOT_ENOUGH, e.getCode() );
        }

        //测试钱够，正常购买的情况
        user.getModuleManager().getAwardModule().addAward( getPlaneTempletById( planeTempletId ).getPrice(), "test" );//给点钱，方便扣除
        System.out.println( module.buy( planeTempletId ) );

        assertEquals( 2, module.getAll().size() );


    }

    /**
     * 测试通过Award模块赠送飞机
     */
    @Test
    public void testCreatePlaneByAwardModule(){

        user.getModuleManager().getAwardModule().addAward( D.DEFAULT_PLANE_ID + ",1", "testCreatePlaneByAwardModule" );
        try {
            //测试重复模板id
            user.getModuleManager().getAwardModule().addAward( D.DEFAULT_PLANE_ID + ",1", "testCreatePlaneByAwardModule" );
        } catch( ClientException e ) {
            assertEquals( e.getCode(), ErrorCode.PLANE_TEMPLET_DUPLICATE );
        }
        //测试无效模板id
        try {
            user.getModuleManager().getAwardModule().addAward( "2,1", "testCreatePlaneByAwardModule" );
        } catch( ClientException e ) {
            assertEquals( e.getCode(), ErrorCode.AWARD_PROP_NOT_FOUND );
        }
    }

    @Test
    public void testCreate() throws Exception{

        module.create( 100501 );
        assertEquals( 1, module.getAll().size() );

        module.create( 100401 );
        module.create( 100301 );
        module.create( 100201 );
        module.create( 100101 );
        //testGetAll();

        assertEquals( 5, module.getAll().size() );

//        printAll( module.getAll() );

        try {
            //测试重复模板id
            module.create( 100101 );
        } catch( ClientException e ) {
            assertEquals( e.getCode(), ErrorCode.PLANE_TEMPLET_DUPLICATE );
        }

        try {
            //测试无效模板id
            module.create( -100101 );
        } catch( ClientException e ) {
            assertEquals( e.getCode(), ErrorCode.PLANE_TEMPLET_NOT_FOUND );
        }

        //module.db.add( new Plane( (long) 1, PlaneTempletCfg.getPlaneTempletById( 100201 ) ) );

    }
}
