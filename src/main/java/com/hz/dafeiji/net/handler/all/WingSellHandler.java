package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONObject;
import com.bbz.tool.common.Transform;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.net.handler.IGameHandler;

/**
 * user         LIUKUN
 * time         2015-1-7 11:54
 * 出售僚机
 */

public class WingSellHandler implements IGameHandler{
    @Override
    public void run( JSONObject request, JSONObject response, User user ){
        long[] wingIdArr = Transform.ArrayType.toLongs(  request.getString( "id" ));
        user.getModuleManager().getWingModule().sell( wingIdArr );
    }


}
