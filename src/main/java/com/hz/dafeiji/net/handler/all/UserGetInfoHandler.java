package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.property.PropertyModule;
import com.hz.dafeiji.net.handler.IGameHandler;


/**
 * user         LIUKUN
 * time         2014-5-28 14:02
 * 获取玩家的关键数据
 */

public class UserGetInfoHandler implements IGameHandler{
    @Override
    public void run( JSONObject request, JSONObject response, User user ){

        JSONObject content = new JSONObject();
        PropertyModule propertyModule = user.getModuleManager().getPropertyModule();
        //UserBaseInfoModule userBaseInfoModule = user.getModuleManager().getUserBaseInfoModule();
        //content.put( "id", userBaseInfoModule.getInfo().getUid() );
        content.put( "g", propertyModule.getCash() );//金币
        content.put( "d", propertyModule.getDiamond() );//钻石
        content.put( "s", propertyModule.getRealStrength() );//体力
        content.put( "ss", propertyModule.getLastCalcStrengthSecond() );//最后一次更新体力的时间（秒）
        content.put( "sm", propertyModule.getStengthMax() );//最大体力，首先写死
        content.put( "p", propertyModule.getPower() );//战斗力
        content.put( "sc", propertyModule.getScore() );//最高积分
        content.put( "sw", propertyModule.getScoreWeek() );//本周最高积分

        response.put( "i", content );
//        response.put( "uId", infoModule.getInfo().getUid() );
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
