package com.hz.dafeiji.ai.user.modules.property;

/**
 * user         LIUKUN
 * time         2014-12-25 11:43
 *
 * 随着时间的增长会自动恢复的体力类
 */

import com.bbz.tool.time.SystemTimer;
import org.joda.time.DateTime;


class Strength{

    /**
     * 体力上限
     */
    public static final int DEFAULT_STRENGTH_MAX = 5;

    /**
     * 每多少秒涨一点体力
     */
    public static final int ADD_STRENGTH_PER_SECOND = 3;

    /**
     * 上次结算后(最后一次修改lastCalcStrengthSecond)剩余的体力
     */
    private int remainStrength;

    /**
     * 最后一次计算体力的时间（秒）
     */
    private int lastCalcStrengthSecond;


    private int strengthMax = DEFAULT_STRENGTH_MAX;


    Strength( int strength, int lastCalcStrengthSecond ){
        this.remainStrength = strength;
        this.lastCalcStrengthSecond = lastCalcStrengthSecond;

    }

    public Strength(){

    }

    /**
     * 仅仅考虑时间因素获取当前玩家的体力，有可能超过上限
     * 注意点：
     * 1、考虑如下情况：100秒增加一点，当前是199秒，此时按规则应增加一点体力，但是
     * 不应该把其余99秒的时间给抹去
     *
     * @return 玩家的当前实际体力
     */
    int getStrength(){


        if( isFull( remainStrength ) ) {
            //System.out.println( "remainStrength=" + remainStrength );
            return remainStrength;
        }

        int timerStrength = (SystemTimer.currentTimeSecond() - lastCalcStrengthSecond) / ADD_STRENGTH_PER_SECOND;

        lastCalcStrengthSecond += timerStrength * ADD_STRENGTH_PER_SECOND;
        remainStrength += timerStrength;

        remainStrength = Math.min( remainStrength, strengthMax );
        return remainStrength;
    }


    /**
     * 通过非时间增长的方式修改玩家的体力，例如吃道具或者消耗体力开始一次游戏
     * 要考虑四种情况
     * <p/>
     * 修改前满，修改后不满           把lastCalcStrengthSecond更新到当前时间
     * 修改前满，修改后满            什么都不用做
     * 修改前不满，修改后不满          什么都不用做
     * 修改前不满，修改后满           什么都不用做
     *
     * @param changeValue 扣除应该为负值
     * @return 修改完毕后剩余的体力
     */
    int changeStrength( int changeValue ){

        int beforeStrength = getStrength();
        int afterStrength = beforeStrength + changeValue;

        if( isFull( beforeStrength ) && !isFull( afterStrength ) ) {

            lastCalcStrengthSecond = SystemTimer.currentTimeSecond();
        }

        remainStrength = afterStrength;
        return afterStrength;
    }

    /**
     * 体力是否满了
     *
     * @param strength 要检测的体力
     * @return true：满了 false:没满
     */
    private boolean isFull( int strength ){
        return strength >= strengthMax;
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
                "remainStrength=" + remainStrength +
                ", lastCalcStrengthSecond=" + new DateTime( lastCalcStrengthSecond * 1000L ) +
                ", strengthMax=" + strengthMax +
                ", realStrength=" + getStrength() +
                '}';
    }

    public void setStrengthMax( int strengthMax ){
        this.strengthMax = strengthMax;
    }
//
//    public static void main( String[] args ){
//        Strength strength = new Strength( 0, 0 );
//        System.out.println( strength.changeStrength( -3 ) );
//        System.out.println( strength.getStrength() );
//    }

}
