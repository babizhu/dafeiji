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
            //扣除1300金币，不够扣应该抛出异常，并且金币数据不变
            module.reduceAward( "500001,1300", "test" );

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

        //清空数据，为下面测试做准备
        module.reduceAward( "500001,700", "test" );
        /////////////////////////////上面是单独属性测试，下面测试多个属性同时操作//////////////////////////////////

        //propertyModule.get


        //增加1000金币,500钻石,剩余金币1000，钻石500
        module.addAward( "500001,1000,500002,500", "test" );
        assertEquals( 1000, propertyModule.getCash() );
        assertEquals( 500, propertyModule.getDiamond() );

        //扣除300金币,剩余700，扣除50钻石，剩余450
        module.reduceAward( "500001,300,500002,50", "test" );
        assertEquals( 700, propertyModule.getCash() );
        assertEquals( 450, propertyModule.getDiamond() );

        try {
            //扣除1300金币,50钻石，金币不够，应该抛出异常，并且金币数据不变
            module.reduceAward( "500001,1300,500002,50", "test" );

        } catch( ClientException e ) {
            assertEquals( e.getCode(), ErrorCode.AWARD_NOT_ENOUGH );
        }
        assertEquals( 700, propertyModule.getCash() );
        assertEquals( 450, propertyModule.getDiamond() );

        //金币尝试扣除负数,会抛出异常,金币和钻石数据都应该不变
        try {
            module.reduceAward( "500001,-300,500002,50", "test" );
        } catch( ClientException e ) {
            assertEquals( ErrorCode.AWARD_REDUCE_COUNT_ILLEGAL, e.getCode() );
        }
        assertEquals( 700, propertyModule.getCash() );
        assertEquals( 450, propertyModule.getDiamond() );


        //尝试扣除的两个道具，其中一个道具id是不存在的，金币和钻石数据都应该不变
        try {
            module.reduceAward( "500322001,300,500002,50", "test" );
        } catch( ClientException e ) {
            assertEquals( ErrorCode.AWARD_PROP_NOT_FOUND, e.getCode() );
        }

        assertEquals( 700, propertyModule.getCash() );
        assertEquals( 450, propertyModule.getDiamond() );

        //增加1000金币,500钻石,剩余金币1000，钻石500
        module.addAward( "500001,1000,500002,500", "test" );
        assertEquals( 1700, propertyModule.getCash() );
        assertEquals( 950, propertyModule.getDiamond() );

    }

    /**
     * 测试赠送飞机
     *
     * @throws Exception
     */
    @Test
    public void testBuyPlane() throws Exception{

    }
}