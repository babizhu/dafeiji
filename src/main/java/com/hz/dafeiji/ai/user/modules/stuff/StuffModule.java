package com.hz.dafeiji.ai.user.modules.stuff;

import com.bbz.tool.common.CountMap;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.modules.ModuleManager;

/**
 * user         LIUKUN
 * time         2015-1-9 10:38
 * 道具材料系统，未避免循环依赖，道具使用的逻辑不放在这里
 */

public class StuffModule{

    //计数map
    private final CountMap<Integer> data;
    private final StuffDataProvider db;

    public StuffModule( ModuleManager moduleManager ){
        db = new StuffDataProvider( moduleManager.getUserName() );
        data = db.findOne();
    }


    /**
     * 增加道具，外层已经做了道具id是否合法的检测
     * @param propId    道具模板id
     * @param count     要增加的数量
     * @return          增加后的数量
     */
    public int addStuff(int propId, int count){

        return data.add( propId, count );
    }

    /**
     * 扣除道具，外层已经做了道具id是否合法的检测
     * @param propId    道具模板id
     * @param count     要扣除的数量
     * @return          扣除后的道具数量
     */
    public int reduceStuff( int propId, int count ){
        if( !isEnough( propId,count ) ){
            throw new ClientException( ErrorCode.STUFF_NOT_ENOUGH );
        }
        return data.add( propId, -count );

    }

    /**
     * 检测道具数量是否足够
     * @param propId        道具id
     * @param needCount         要检测的数量
     * @return  true：足够
     *          false:不够
     */
    public boolean isEnough( int propId, int needCount ){
        int count = data.get( propId );
        return count > needCount;
    }


    /**
     * 获取玩家某个道具的数量,map中不存在此道具则返回0
     * @param stuffTempletId            道具模板id
     */
    public int getCount( int stuffTempletId ){
        return data.get( stuffTempletId );
    }

    public CountMap<Integer> getAllStuff(){
        return data;
    }

    void removeAll(){
        db.remove();
        data.clear();
    }
}
