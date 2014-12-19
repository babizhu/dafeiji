package com.hz.dafeiji.net.handler;


import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

/**
 * user         LIUKUN
 * time         2014-6-6 17:03
 * 检测玩家的昵称是否被占用
 */

public class NickNameCheckHandler implements INoLoginHandler{
    @Override
    public void run( String request, JSONObject response, ChannelHandlerContext ctx ){
//        String nickName = request.getNickNameCheck().getNickName();
//
//
//        User.NickNameCheckResponse.Builder result = User.NickNameCheckResponse.newBuilder();
//        result.setIsDuplicate( true );
//
//
//        /****************************设返回值***************************/
//        responseBuilder.setNickNameCheck( result );
    }
}
