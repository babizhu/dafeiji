package com.hz.dafeiji.ai.user.modules.property;

/**
 * user         LIUKUN
 * time         2014-12-25 11:43
 *
 * 随着时间的增长会自动恢复的体力类
 */

import com.bbz.tool.time.SystemTimer;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import org.joda.time.DateTime;


class Strength{

    public static final int DEFAULT_STRENGTH_MAX = 5;
    /**
     * 每多少秒涨一点体力
     */
    public static final int ADD_STRENGTH_PER_SECOND = 1;

    /**
     * 截止到lastCalcStrengthSecond的体力
     */
    private int strength;

    /**
     * 最后一次计算体力的时间（秒）
     */
    private int lastCalcStrengthSecond;


    private int strengthMax = DEFAULT_STRENGTH_MAX;


    Strength( int strength, int lastCalcStrengthSecond ){
        this.strength = strength;
        this.lastCalcStrengthSecond = lastCalcStrengthSecond;

    }

    public Strength(){

    }

    /**
     * 获取当前玩家的体力
     *
     * @return 当前玩家的真实体力
     */
    int getRealStrength(){
        strength += (SystemTimer.currentTimeSecond() - lastCalcStrengthSecond) / ADD_STRENGTH_PER_SECOND;
        strength = Math.min( strength, strengthMax );
        lastCalcStrengthSecond = SystemTimer.currentTimeSecond();
        return strength;
    }


    /**
     * 扣除体力
     *
     * @param reduceValue 要扣除的体力值
     * @return 剩余的体力值
     */
    int reduceStrength( int reduceValue ){
        int realStrength = getRealStrength();
        if( reduceValue > realStrength ) {
            throw new ClientException( ErrorCode.USER_STRENGTH_NOT_ENOUGH );

        }
        realStrength -= reduceValue;
        strength = realStrength;
        return strength;
    }

    int getLastCalcStrengthSecond(){
        return lastCalcStrengthSecond;
    }

    public int getStrengthMax(){
        return strengthMax;
    }

    @Override
    public String toString(){
        return "Strength{" +
                "strength=" + strength +
                ", lastCalcStrengthSecond=" + new DateTime( lastCalcStrengthSecond * 1000L ) +
                ", strengthMax=" + strengthMax +
                ", realStrength=" + getRealStrength() +
                '}';
    }
}
