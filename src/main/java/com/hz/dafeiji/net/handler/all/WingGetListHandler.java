package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.wing.Wing;
import com.hz.dafeiji.net.handler.IGameHandler;

import java.util.Map;

/**
 * user         LIUKUN
 * time         2015-1-7 11:54
 */

public class WingGetListHandler implements IGameHandler{
    @Override
    public void run( JSONObject request, JSONObject response, User user ){
        JSONArray wingList = new JSONArray();
        Map<Long, Wing> all = user.getModuleManager().getWingModule().getAll();
        for( Wing wing : all.values() ) {
            JSONObject wingJson = getJsonObject( wing );


            wingList.add( wingJson );
        }
        response.put( "l", wingList );
    }

    public static JSONObject getJsonObject( Wing wing ){
        JSONObject wingJson = new JSONObject();


        wingJson.put( "id", wing.getId() );//唯一id
        wingJson.put( "tid", wing.getTemplet().getId() );//模板id
        wingJson.put( "l", wing.getLevel() );//等级
        wingJson.put( "atk", wing.getTemplet().getId() );//攻击
        wingJson.put( "spd", wing.getTemplet().getAspd() );//速度
        return wingJson;
    }


}
