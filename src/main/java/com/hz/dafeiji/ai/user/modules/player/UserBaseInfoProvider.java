package com.hz.dafeiji.ai.user.modules.player;


import com.bbz.tool.db.AbstractDataProviderWithUserName;
import com.hz.dafeiji.ai.user.UserStatus;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         2014-12-19 15:09
 */

public class UserBaseInfoProvider extends AbstractDataProviderWithUserName<UserBaseInfo>{

    private static final String TABLE_NAME = "userBase";

    /**
     * @param uname 玩家名称
     */
    public UserBaseInfoProvider( String uname ){
        super( TABLE_NAME, uname );
    }


    @Override
    protected UserBaseInfo decode( DBObject obj ){
        UserBaseInfo info = new UserBaseInfo();
        if( obj == null ) {
            return info;
        }

        info.setNickName( (String) obj.get( "nickName" ) );
        info.setUserName( (String) obj.get( "_id" ) );
        info.setPass( (String) obj.get( "pass" ) );
        info.setUid( (int) obj.get( "uid" ) );
        info.setUserStatus( UserStatus.valueOf( (String) obj.get( "userStatus" ) ) );

        return info;
    }

    @Override
    protected DBObject encode( UserBaseInfo info ){

        DBObject obj = new BasicDBObject();
        obj.put( "nickName", info.getNickName() );
        obj.put( "_id", info.getUserName() );
        obj.put( "pass", info.getPass() );

        obj.put( "uid", info.getUid() );
        obj.put( "userStatus", info.getUserStatus().toString() );
        return obj;
    }

    /**
     * 判断数据库是否存在同名的昵称,用户名无需判断，在构造函数中已经判断过了
     *
     * 非常的耗资源，取消判断可提升百倍效率

     * @param nickName
     * @return  true：有重复
     *          false：无重复
     */
    boolean isDuplicate( String nickName ){
        DBObject condition = new BasicDBObject( "nickName", nickName );
        return collection.findOne( condition ) != null;

//        QueryBuilder query = new QueryBuilder();
//        query.or( new BasicDBObject( "_id", uname ), new BasicDBObject( "nickName", nickName ) );
//
//        //DBObject condition = new BasicDBObject( "_id", uname ).append( "nickName", nickName );
//        return ( collection.find( query.get() ).hasNext() );
    }
}
