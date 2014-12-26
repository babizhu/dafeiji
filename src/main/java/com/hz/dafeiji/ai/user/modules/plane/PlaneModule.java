package com.hz.dafeiji.ai.user.modules.plane;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-12-25 16:56
 * <p/>
 * 机甲模块，也就是飞机模块，也就是传统的英雄模块
 */

public class PlaneModule{
    private final PlaneDataProvider db;

    /**
     * 所有的英雄
     */
    private List<Plane> planes;

    public PlaneModule( String uname ){
        db = new PlaneDataProvider( uname );
        planes = db.getListAll();
    }
}
