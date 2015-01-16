package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.net.handler.IGameHandler;

/**
 * user         LIUKUN
 * time         2015-1-7 11:54
 * 设置某个僚机的状态，出战或者休息
 */

public class WingDecomposeHandler implements IGameHandler{
    @Override
    public void run( JSONObject request, JSONObject response, User user ){
        long wingId = request.getLong( "id" );
        user.getModuleManager().getWingModule().decompose( wingId );
    }


}
