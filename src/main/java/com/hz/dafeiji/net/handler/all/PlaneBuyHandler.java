package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.net.handler.IGameHandler;

/**
 * user         LIUKUN
 * time         2015-1-7 11:54
 */

public class PlaneBuyHandler implements IGameHandler{
    @Override
    public void run( JSONObject request, JSONObject response, User user ){

        int planeTempletId = request.getInteger( "t" );
        user.getModuleManager().getPlaneModule().buy( planeTempletId );
    }
}
