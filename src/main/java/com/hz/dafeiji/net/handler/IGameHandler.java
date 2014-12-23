package com.hz.dafeiji.net.handler;


import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.user.User;

/**
 * user         LIUKUN
 * time         2014-6-6 19:05
 */
public interface IGameHandler extends IHandler{
    public abstract void run( JSONObject request, JSONObject response, User user );
}
