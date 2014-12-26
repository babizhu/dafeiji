package com.hz.dafeiji.ai.user.modules.property;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StrengthTest{

    @Test
    public void testGetStrength() throws Exception{

        Strength strength = new Strength( 0, 0 );
        int realStrength = 0;
        int strengthMax = strength.getStrengthMax();


        realStrength = strength.getRealStrength();//初始输入下，获得满体力
        assertEquals( strengthMax, realStrength );

        strength.reduceStrength( 3 ); //扣除3点体力
        realStrength = strength.getRealStrength();
        assertEquals( strengthMax - 3, realStrength );


        Thread.sleep( 2 * Strength.ADD_STRENGTH_PER_SECOND * 1000 );//恢复2点体力
        realStrength = strength.getRealStrength();
        assertEquals( strengthMax - 3 + 2, realStrength );

        Thread.sleep( 10 * Strength.ADD_STRENGTH_PER_SECOND * 1000 );//故意等待10秒
        realStrength = strength.getRealStrength();
        assertEquals( strengthMax, realStrength );

        /**
         *      下面测试一种极端情况
         *      玩家体力达到上限，此时下线，一年后上线
         *      此时开始扣体力（1点），那么一个恢复单位时间后，体力应该重新回到max值
         *
         *      不知道怎么测试了。应该没问题
         */

    }

    @Test
    public void testReduceStrength() throws Exception{

    }
}