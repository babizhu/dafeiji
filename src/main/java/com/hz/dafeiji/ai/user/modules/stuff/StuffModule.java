package com.hz.dafeiji.ai.user.modules.stuff;

import com.hz.dafeiji.ai.user.modules.ModuleManager;

/**
 * user         LIUKUN
 * time         2015-1-9 10:38
 * 道具材料系统
 */

public class StuffModule{


    public StuffModule( ModuleManager moduleManager ){

    }

    public int reduceStuff( int propId, int count ){
        return 0;
    }

    /**
     * 检测道具数量是否足够
     * @param propId        道具id
     * @param count         要检测的数量
     * @return  true：足够
     *          false:不够
     */
    public boolean isEnough( int propId, int count ){
        return true;
    }
}
