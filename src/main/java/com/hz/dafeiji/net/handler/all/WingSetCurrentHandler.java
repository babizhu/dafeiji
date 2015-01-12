package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.net.handler.IGameHandler;

/**
 * user         LIUKUN
 * time         2015-1-7 11:54
 * 设置当前出战僚机
 */

public class WingSetCurrentHandler implements IGameHandler{
    @Override
    public void run( JSONObject request, JSONObject response, User user ){
        long wingId = request.getLong( "id" );
        user.getModuleManager().getWingModule().setCurrentWing( wingId );
    }


}
