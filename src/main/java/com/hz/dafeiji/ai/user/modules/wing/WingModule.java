package com.hz.dafeiji.ai.user.modules.wing;

import com.hz.dafeiji.ai.user.modules.ModuleManager;
import com.hz.dafeiji.ai.user.modules.property.PropertyModule;

/**
 * user         LIUKUN
 * time         2015-1-7 14:24
 * 僚机系统
 */

public class WingModule{

    private final PropertyModule propertyModule;

    public WingModule( ModuleManager moduleManager ){
        this.propertyModule = moduleManager.getPropertyModule();
    }

    /**
     * 僚机升级
     *
     * @param id
     */
    public void levelUp( long id ){

    }

    /**
     * 计算可以升级的最大等级
     *
     * @param wing 要升级的飞机
     * @param exp  可用于此次升级的经验
     * @return 当前条件下（经验，金币）可升的最大等级
     */
    private boolean calcMaxLevel( Wing wing, int exp ){
//        int cash =
//        if(  )
//        int maxLevel = 1;
//        int needExp =
        return true;
    }

    /**
     * 出售僚机
     * @param id    要出售的僚机id
     * @return 获取的金币
     */
    public int sell( long id ){
        return 0;
    }


    /**
     * 购买僚机
     *
     * @param wingTempletId
     */
    public void buy( int wingTempletId ){

    }


}
