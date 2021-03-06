package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.user.GameWorld;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.player.UserBaseInfo;
import com.hz.dafeiji.net.handler.IGameHandler;


/**
 * user         LIUKUN
 * time         2014-5-28 14:02
 */

public class UserLoginHandler implements IGameHandler{
    @Override
    public void run( JSONObject request, JSONObject response, User user ){
        String uname = request.getString( "uName" );
        String pass = request.getString( "uPass" );
//        String devId = request.getString( "uDevId" );

        User loginUser = GameWorld.INSTANCE.login( uname, pass );
        UserBaseInfo info = loginUser.getUserBaseInfo();


        response.put( "h", loginUser.getSesseion() );
        response.put( "uId", info.getUid() );


//        System.out.println( "LoginHandler.run" );
    }

//    @Override
//    public void run( String request, JSONObject response, ChannelHandlerContext ctx ){
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
//    }


}
