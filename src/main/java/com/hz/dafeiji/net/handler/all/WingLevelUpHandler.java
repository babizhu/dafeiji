package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONObject;
import com.bbz.tool.common.Transform;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.net.handler.IGameHandler;

/**
 * user         LIUKUN
 * time         2015-1-7 11:54
 */

@SuppressWarnings("UnusedDeclaration")
public class WingLevelUpHandler implements IGameHandler{
    @Override
    public void run( JSONObject request, JSONObject response, User user ){
        //用逗号分隔的经验卡道具ID，允许为""，不允许为null
        int[] stuffArr = Transform.ArrayType.toInts( request.getString( "st" ) );
        //用逗号分隔的要被吞噬的僚机唯一id，允许为""，不允许为null
        long[] swallowWings = Transform.ArrayType.toLongs( request.getString( "w" ) );
        long wingId = request.getLong( "id" );
        user.getModuleManager().getWingModule().levelUp( wingId, stuffArr, swallowWings );
    }
}
