package com.hz.dafeiji.ai.user.modules.award;

import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.property.PropertyModule;
import com.hz.util.D;
import org.junit.After;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

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

        int cash = propertyModule.getCash();
        assertEquals( 0, cash );
        //增加1000金币,剩余1000
        module.addAward( "500001,1000", "test" );
        assertEquals( 1000, propertyModule.getCash() );
        //扣除300金币,剩余700
        module.reduceAward( "500001,300", "test" );
        assertEquals( 700, propertyModule.getCash() );

        try {
            //扣除1300金币，应该抛出异常，并且金币数据不变
            module.reduceAward( "500001,1300", "test" );

            assertEquals( 1300, propertyModule.getCash() );
        } catch( ClientException e ) {
            assertEquals( e.getCode(), ErrorCode.AWARD_NOT_ENOUGH );
        }
        assertEquals( 700, propertyModule.getCash() );

        //尝试扣除负数,会抛出异常
        try {
            module.reduceAward( "500001,-1300", "test" );
        } catch( ClientException e ) {
            assertEquals( ErrorCode.AWARD_REDUCE_COUNT_ILLEGAL, e.getCode() );
        }

        //尝试扣除不存在的属性
        try {
            module.reduceAward( "50000001,1300", "test" );
        } catch( ClientException e ) {
            assertEquals( ErrorCode.AWARD_PROP_NOT_FOUND, e.getCode() );
        }

        /////////////////////////////上面是单独属性测试，下面测试多个属性同时操作//////////////////////////////////

        int diamond = propertyModule.getDiamond();
        //propertyModule.get

        assertEquals( 0, cash );
        //增加1000金币,剩余1000
        module.addAward( "500001,1000", "test" );
        assertEquals( 1000, propertyModule.getCash() );
        //扣除300金币,剩余700
        module.reduceAward( "500001,300", "test" );
        assertEquals( 700, propertyModule.getCash() );

        try {
            //扣除1300金币，应该抛出异常，并且金币数据不变
            module.reduceAward( "500001,1300", "test" );

            assertEquals( 1300, propertyModule.getCash() );
        } catch( ClientException e ) {
            assertEquals( e.getCode(), ErrorCode.AWARD_NOT_ENOUGH );
        }
        assertEquals( 700, propertyModule.getCash() );

        //尝试扣除负数,会抛出异常
        try {
            module.reduceAward( "500001,-1300", "test" );
        } catch( ClientException e ) {
            assertEquals( ErrorCode.AWARD_REDUCE_COUNT_ILLEGAL, e.getCode() );
        }

        //尝试扣除不存在的属性
        try {
            module.reduceAward( "50000001,1300", "test" );
        } catch( ClientException e ) {
            assertEquals( ErrorCode.AWARD_PROP_NOT_FOUND, e.getCode() );
        }

    }

    @Test
    public void testReduceAward() throws Exception{

    }
}