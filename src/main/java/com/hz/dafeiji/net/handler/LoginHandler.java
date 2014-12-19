package com.hz.dafeiji.net.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;


/**
 * user         LIUKUN
 * time         2014-5-28 14:02
 */

public class LoginHandler implements INoLoginHandler{

    @Override
    public void run( String request, JSONObject response, ChannelHandlerContext ctx ){
        /****************************获取参数***************************/
//        LoginRequest login = request.getLogin();
//        String uname = login.getUserName();
//        String password = login.getPassword();
//        System.out.println( "uname " + uname + " pass " + password );
//
//
//        /****************************逻辑处理***************************/
//        User.LoginResponse.Builder result = User.LoginResponse.newBuilder();
//        result.setRet( RandomUtil.getInt( 5000 ) );
//
//        /****************************设返回值***************************/
//        response.setLogin( result );
//
//        if( uname.equals( "bbz" ) ) {
//            changeDispatcher( ctx );
//        }
    }

    /**
     * 玩家成功登陆后，需要修改dispatcher
     * @param ctx
     */
    private void changeDispatcher( ChannelHandlerContext ctx ){
//        ChannelPipeline pipeline = ctx.pipeline();
//        pipeline.addLast( new GameServerDispatcher() );
//        pipeline.remove(  NoLoginDispatcher.class );
//        System.out.println( "登陆成功，转向正常的游戏逻辑处理句柄" );
    }
}
