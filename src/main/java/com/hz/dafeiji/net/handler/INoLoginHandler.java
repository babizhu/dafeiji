package com.hz.dafeiji.net.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;


/**
 * user         LIUKUN
 * time         2014-6-6 11:49
 * 所有无需登录就可以执行的处理器的接口
 */

public interface INoLoginHandler extends IHandler{

    public abstract void run( String request, JSONObject response, ChannelHandlerContext ctx );
}
