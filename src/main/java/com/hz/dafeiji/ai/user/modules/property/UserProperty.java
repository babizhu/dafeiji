package com.hz.dafeiji.ai.user.modules.property;

import lombok.Data;

/**
 * user         LIUKUN
 * time         2014-4-9 20:30
 */
@Data
class UserProperty{


    /**
     * 最大体力
     */
    private int strengthMax;
    /**
     * 体力
     */
    private int strength;

    /**
     * 历史最高分
     */
    private int score;

    /**
     * 本周最高分
     */
    private int scoreWeek;

    /**
     * 战斗力
     */
    private int power;

    /**
     * 钻石
     */
    private int diamond;


    /**
     * 经验
     */
    private int exp;

    /**
     * 铜钱
     */
    private int cash;

    /**
     * vip等级
     */
    private int vip;

    //private INonBlockingConnection  con;


//    int getLevel(){
//        int[] data = new int[]{1, 10, 100, 100};
//        return MiscUtil.calcLevel( data, exp, 1 );
//    }


    public void setScore( int score ){
        if( this.score < score ) {
            this.score = score;
        }
    }

    public void setScoreWeek( int score ){
        if( this.scoreWeek < score ) {
            this.scoreWeek = score;
        }
    }

    public static void main( String[] args ){
        UserProperty property = new UserProperty();
        property.setScoreWeek( 100 );
        ;
        System.out.println( property.getScoreWeek() );
        property.setScoreWeek( 10 );
        ;
        System.out.println( property.getScoreWeek() );
    }


}