package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.net.handler.IGameHandler;


/**
 * user         LIUKUN
 * time         2014-5-28 14:02
 * 获取排行榜数据
 */

@SuppressWarnings("UnusedDeclaration")
public class RankGetTopHandler implements IGameHandler{
    @Override
    public void run( JSONObject request, JSONObject response, User user ){

        JSONArray topList = new JSONArray();
        for( int i = 0; i < 5; i++ ) {

            JSONObject friendJson = new JSONObject();

            friendJson.put( "icon", "http://192.168.1.55:8000/icon/1.jpg" );//头像
            friendJson.put( "nick", "aabb" );//昵称
            friendJson.put( "sw", 12345 );//本周最高分
            friendJson.put( "p", 100301 );//飞机模板id
            friendJson.put( "w", 200402 );//僚机模板id
            friendJson.put( "st", 1 );//是否可以赠送体力，1：可赠送，0：不可赠送
            topList.add( friendJson );
        }

        response.put( "top", topList );

    }


}
