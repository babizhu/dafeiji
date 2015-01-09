package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.plane.Plane;
import com.hz.dafeiji.net.handler.IGameHandler;

import java.util.Map;

/**
 * user         LIUKUN
 * time         2015-1-7 11:54
 */

public class PlaneGetListHandler implements IGameHandler{
    @Override
    public void run( JSONObject request, JSONObject response, User user ){
        JSONArray planeList = new JSONArray();
        Map<Long, Plane> all = user.getModuleManager().getPlaneModule().getAll();
        for( Plane plane : all.values() ) {
            JSONObject planeJson = getJsonObject( plane );


            planeList.add( planeJson );
        }
        response.put( "l", planeList );
    }

    public static JSONObject getJsonObject( Plane plane ){
        JSONObject planeJson = new JSONObject();


        planeJson.put( "id", plane.getId() );//唯一id
        planeJson.put( "tid", plane.getTemplet().getId() );//模板id
        planeJson.put( "l", plane.getLevel() );//等级
        planeJson.put( "atk", plane.getTemplet().getId() );//攻击
        planeJson.put( "hp", plane.getHp() );//HP
        planeJson.put( "spd", plane.getTemplet().getAspd() );//速度
        return planeJson;
    }


}
