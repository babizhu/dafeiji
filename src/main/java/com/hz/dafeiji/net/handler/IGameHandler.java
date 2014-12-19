package com.hz.dafeiji.net.handler;


import com.alibaba.fastjson.JSONObject;

/**
 * user         LIUKUN
 * time         2014-6-6 19:05
 */
public interface IGameHandler extends IHandler{
    public abstract void run( String request, JSONObject response, Object user );
}
