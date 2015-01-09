package com.hz.dafeiji.ai.user.modules.wing;

import com.hz.dafeiji.ai.user.modules.ModuleManager;
import com.hz.dafeiji.ai.user.modules.plane.Plane;

import java.util.Map;

/**
 * user         LIUKUN
 * time         2015-1-7 14:24
 * 僚机系统
 */

public class WingModule{

    private final WingDataProvider db;

    /**
     * 所有的飞机
     */
    private Map<Long, Plane> planes;

    /**
     * 当前出战的飞机
     */
    private Plane currentPlane;
    //private CurrentPlaneData currentPlaneData;

//    private final ModuleManager moduleManager;
//    private final AwardModule awardModule;


    public WingModule( ModuleManager moduleManager ){
        db = new WingDataProvider( "xx" );
    }

    /**
     * 僚机升级
     *
     * @param id    被升级的僚机id
     */
    public void levelUp( long id, int[] stuffs, long[] wings ){

//        int exp = calcExp( stuffs, wings );
    }


    /**
     * 计算玩家上传的经验卡已经僚机所产生的经验
     * @param stuffs    经验卡id
     * @param wings     僚机唯一id
     * @return          返回stuffs以及wings所产生的经验
     */
    private int calcExp( int[] stuffs, Wing[] wings ){
        return 0;
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
