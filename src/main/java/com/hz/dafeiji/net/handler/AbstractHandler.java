package com.hz.dafeiji.net.handler;


import com.alibaba.fastjson.JSONObject;

/**
 * user         LIUKUN
 * time         2014-5-28 19:54
 */

public abstract class AbstractHandler{
    public abstract void run( String request, JSONObject response );
}
