package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.net.handler.IGameHandler;

/**
 * user         LIUKUN
 * time         2015-1-7 11:54
 * 僚机合成
 */

public class WingComposeHandler implements IGameHandler{
    @Override
    public void run( JSONObject request, JSONObject response, User user ){
        int templetId = request.getInteger( "tid" );

        user.getModuleManager().getWingModule().compose( templetId );
    }


}
