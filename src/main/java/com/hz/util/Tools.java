package com.hz.util;

import com.alibaba.fastjson.JSONObject;
import com.bbz.tool.time.SystemTimer;
import org.joda.time.LocalDate;

/**
 * Created by Valen on 2015/1/5.
 *
 * 工具类
 */
public class Tools {

    /**
     * 检查请求数据里是否包含必要的key
     * @param request JSONObject的request对象
     * @param args 需要检查的key名称列表,逗号分割
     * @return true 通过 | false
     */
    public static boolean reqParamCheck(JSONObject request, String args){
        boolean chk = true;
        for(String key : args.split(",")){
            if(!request.containsKey(key.trim())){
                chk = false;
                break;
            }
        }
        return chk;
    }

    public static void main(String[] args){
        System.out.println(isCurrentWeek( 1 ));
    }


    public static boolean isCurrentWeek( int time1 ){

        LocalDate day = new LocalDate( );
        day = day.withDayOfWeek( 5+1 );

        long sec = (day.toDate().getTime() - 1) / 1000;
        System.out.println(  );
        System.out.println( day.toDate() );

        if( time1 < sec && SystemTimer.currentTimeSecond() >= sec ){
            return false;
        }

        return true;


    }
}
