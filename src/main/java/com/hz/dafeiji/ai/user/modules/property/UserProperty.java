package com.hz.dafeiji.ai.user.modules.property;

import com.bbz.tool.time.SystemTimer;

/**
 * user         LIUKUN
 * time         2014-4-9 20:30
 */

class UserProperty{


    /**
     * 体力
     */
    private Strength strength;

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
//    private int exp;

    /**
     * 铜钱
     */
    private int cash;

    /**
     * vip等级
     */
    private int vip;

    /**
     * 能源
     */
    private int energy;

    public int getDistance(){
        return distance;
    }

    public void setDistance( int distance ){
        this.distance = distance;
    }

    public int getDistanceWeek(){
        return distanceWeek;
    }

    public void setDistanceWeek( int distanceWeek ){
        this.distanceWeek = distanceWeek;
    }

    /**
     * 历史最远距离
     */
    private int distance;

    /**
     * 本周最远距离
     */
    private int distanceWeek;

    /**
     * 产生记录的时间（秒）
     */
    private int recordTime;


    //private INonBlockingConnection  con;


//    int getLevel(){
//        int[] data = new int[]{1, 10, 100, 100};
//        return MiscUtil.calcLevel( data, exp, 1 );
//    }


    /**
     * 获取当前体力
     */
    public Strength getStrength(){

        return strength;
    }

    public void setScore( int score ){
        if( this.score < score ) {
            this.score = score;
        }
    }

    public void setScoreWeek( int score ){
        if( this.scoreWeek <= score ) {
            this.scoreWeek = score;
            recordTime = SystemTimer.currentTimeSecond();
        }else {
//            if( !TimeUtil.isCurrentWeek(recordTime, SystemTimer.currentTimeSecond() ) ){
//                this.scoreWeek = score;
//                recordTime = SystemTimer.currentTimeSecond();
//            }
        }

    }


    public int getScoreWeek(){
        return scoreWeek;
    }


    public void setDiamond( int diamond ){
        this.diamond = diamond;
    }

    public void setStrength( Strength strength ){
        this.strength = strength;
    }

    public void setPower( int power ){
        this.power = power;
    }

    public void setVip( int vip ){
        this.vip = vip;
    }

    public int getDiamond(){
        return diamond;
    }

    public int getScore(){
        return score;
    }

    public int getPower(){
        return power;
    }

    public int getVip(){
        return vip;
    }

//    public int getExp(){
//        return exp;
//    }

    public int getCash(){
        return cash;
    }


    @Override
    public String toString(){
        return "UserProperty{" +
                "strength=" + strength +
                ", score=" + score +
                ", scoreWeek=" + scoreWeek +
                ", power=" + power +
                ", diamond=" + diamond +
                ", distance=" + distance +
                ", distanceWeek=" + distanceWeek +
                ", cash=" + cash +
                ", vip=" + vip +
                '}';
    }


    public void setCash( int cash ){
        this.cash = cash;
    }


    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }
}