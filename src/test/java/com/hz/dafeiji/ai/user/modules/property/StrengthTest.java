package com.hz.dafeiji.ai.user.modules.property;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StrengthTest{

    @SuppressWarnings("PointlessArithmeticExpression")
    @Test
    public void testGetStrength() throws Exception{

        Strength strength = new Strength( 0, 0 );
        strength.setStrengthMax( 5 );//设置玩家的最大体力为5，方便下面测试

        int realStrength;
        int strengthMax = strength.getStrengthMax();


        /**
         * 初始情况下，应该获得满体力（剩余5）
         */
        realStrength = strength.getStrength();
        assertEquals( strengthMax, realStrength );

        /**
         * 扣除2点体力，（剩余3）
         */
        strength.changeStrength( -2 ); //扣除3点体力
        realStrength = strength.getStrength();
        assertEquals( 3, realStrength );


        /**
         * 通过等待2个时间单位，直接把体力回满（剩余5）
         */
        Thread.sleep( 2 * Strength.ADD_STRENGTH_PER_SECOND * 1000 );//等待2个单位
        realStrength = strength.getStrength();
        assertEquals( strengthMax, realStrength );

        /**
         * 测试通过时间自然增长的方式会否超过strengthMax
         * 现在是满体力（剩余5），然后主动等待1个时间单位
         */
        Thread.sleep( 1 * Strength.ADD_STRENGTH_PER_SECOND * 1000 );
        realStrength = strength.getStrength();
        assertEquals( strengthMax, realStrength );

        /**
         *      假设玩家吃道具，直接增加4个体力
         */
        strength.changeStrength( 4 );
        assertEquals( strengthMax + 4, strength.getStrength() );

        /**
         * 当前体力已经超过上限了（剩余9），等待一个时间单位，看看体力是否会自然增长，正常情况下应该不会
         */
        Thread.sleep( 1 * Strength.ADD_STRENGTH_PER_SECOND * 1000 );
        assertEquals( 9, strength.getStrength() );

        /**
         * 当前体力已经超过上限了，再次增加2个体力（剩余11）
         */
        strength.changeStrength( 2 );
        assertEquals( 11, strength.getStrength() );

        /**
         * 此时用掉1个体力（剩余10）,在等待1个时间单位，看看体力是否会自然恢复1个时间单位，正常情况下应该不会
         */
        strength.changeStrength( -1 );
        Thread.sleep( 1 * Strength.ADD_STRENGTH_PER_SECOND * 1000 );
        assertEquals( 10, strength.getStrength() );

        /**
         * 此时用掉9个体力（让体力低于上限，剩余1）,在等待1个时间单位（剩余2），看看体力是否会自然恢复1个时间单位
         */
        strength.changeStrength( -9 );
        Thread.sleep( 1 * Strength.ADD_STRENGTH_PER_SECOND * 1000 );
        assertEquals( 2, strength.getStrength() );

        /**
         * 等待1个时间单位+2秒的时间(2秒不到一个时间单位)，看看体力恢复情况（剩余3）
         */
        Thread.sleep( (1 * Strength.ADD_STRENGTH_PER_SECOND + 2) * 1000 );
        assertEquals( 3, strength.getStrength() );

        /**
         * 体力不满的情况下，继续扣2个体力（剩余1）
         */
        strength.changeStrength( -2 );
        assertEquals( 1, strength.getStrength() );
        /**
         * 等待1个时间单位-1秒的时间，看看体力恢复情况（剩余2）
         */
        Thread.sleep( (1 * Strength.ADD_STRENGTH_PER_SECOND - 1) * 1000 );
        assertEquals( 2, strength.getStrength() );

    }

    /**
     * 测试如下情况
     * 玩家剩余3个体力，并且lastCalcStrengthSecond是很久以前
     * 突然吃道具长满体力
     * 又用掉6个体力,低于上限
     * 此时看看当前体力是多少，会不会因为lastCalcStrengthSecond是很久以前而马上回复成满体力，
     *
     * @throws Exception
     */
    @Test
    public void testReduceStrength() throws Exception{
        //当前体力3，并且lastCalcStrengthSecond是100天以前
        Strength strength = new Strength( 3, (int) (new DateTime().plusDays( -100 ).getMillis() / 1000) );
        strength.setStrengthMax( 5 );
        strength.changeStrength( 2 );
        strength.changeStrength( -3 );
        assertEquals( 4, strength.getStrength() );
    }
}