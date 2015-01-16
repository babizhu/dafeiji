package com.hz.util.count;

/**
 * user         LIUKUN
 * time         2015-1-16 16:35
 *
 * 一个计数器，里面的内容在某个时间段内有效，过了这个时间段则主动清0
 */

public class TimerCounter{

    private final int timeoutPoint;
    private int timeStamp;
    private int score;
    private int scoreWeek;

    public TimerCounter( int timeoutPoint ){
        this.timeoutPoint = timeoutPoint;
    }

    public int getScore(){
        if( isTimeout() ){
            score = 0;
        }
        return score;
    }

    private boolean isTimeout(){
        return true;
    }
}
