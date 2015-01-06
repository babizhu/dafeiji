package com.hz.dafeiji.ai.user.player;


import com.bbz.tool.db.MongoUtil;
import com.hz.dafeiji.ai.user.UserStatus;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         2014-12-19 15:09
 */

class UserBaseInfoProvider{

    private static final String TABLE_NAME = "userBase";
    private final DBCollection collection = MongoUtil.INSTANCE.getDB().getCollection( TABLE_NAME );

    /**
     * 根据玩家名查找玩家的基础信息
     * @param userName      玩家名
     * @return 玩家的基础信息
     */
    UserBaseInfo findByName( String userName ){
        BasicDBObject condition = new BasicDBObject( "_id", userName );
        return query( condition );
    }

    UserBaseInfo findByNick( String nickName ){
        BasicDBObject condition = new BasicDBObject( "nickName", nickName );
        return query( condition );
    }

    private UserBaseInfo query( BasicDBObject condition ){
        return decode( collection.findOne( condition ) );
    }

    protected UserBaseInfo decode( DBObject obj ){

        /**
         * 数据库中不存在此用户
         */
        if( obj == null ) {
            return null;
        }
        UserBaseInfo info = new UserBaseInfo();
        info.setNickName( (String) obj.get( "nickName" ) );
        info.setUserName( (String) obj.get( "_id" ) );
        info.setPass( (String) obj.get( "pass" ) );
        info.setUid( (long) obj.get( "uid" ) );
        info.setUserStatus( UserStatus.valueOf( (String) obj.get( "userStatus" ) ) );

        return info;
    }


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
     * 判断数据库是否存在同名的玩家名和昵称
     * @param userName 玩家名
     * @param nickName 昵称
     * @return true：有重复
     * false：无重复
     */
    boolean userIsDuplicate( String userName, String nickName ){
        BasicDBObject condition = new BasicDBObject( "_id", userName );
        condition.append( "nickName", nickName );
        return query( condition ) != null;
    }

    /**
     * 保存注册玩家的信息
     *
     * @param info 玩家信息实体类
     */
    void save( UserBaseInfo info ){
        collection.save( encode( info ) );
    }

    /**
     * 删除玩家信息
     *
     * @param userName
     */
    public void remove( String userName ){
        DBObject conditon = new BasicDBObject( "_id", userName );
        collection.remove( conditon );
    }
}
