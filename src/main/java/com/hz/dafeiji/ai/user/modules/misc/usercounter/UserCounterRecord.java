package com.hz.dafeiji.ai.user.modules.misc.usercounter;


import com.bbz.tool.common.CountMap;
import com.bbz.tool.time.SystemTimer;
import com.bbz.tool.time.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * user         LIUKUN
 * time         2014/5/2 0002 12:55
 */
@AllArgsConstructor
@Getter
@ToString
class UserCounterRecord{


    private final CountMap<String> counterData;

    /**
     * 整个数据的时间戳
     * 如果此时间戳和当天时间不是同一天，则必须在进行任何操作前，清空所有的数据，如此就能保证一个时间戳管理所有数据
     */
    private int timeStamp;

    /**
     * 获取数据
     *
     * @param key key
     * @return 数量
     */
    int get( String key ){

        return counterData.get( key );
    }

    /**
     * put数据
     *
     * @param key   key
     * @param value 要直接设置的值
     */
    void put( String key, int value ){

        counterData.put( key, value );
        timeStamp = SystemTimer.currentTimeSecond();
    }

    /**
     * 累加数据
     *
     * @param key    key
     * @param change 要改变的值
     * @return 添加之后的结果
     */
    int add( String key, int change ){
        timeStamp = SystemTimer.currentTimeSecond();
        return counterData.add( key, change );
    }


    void clear(){
        counterData.clear();
        timeStamp = 0;
    }

    boolean isToday(){
        return TimeUtil.isToday( timeStamp );
    }
}
