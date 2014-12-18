package com.hz.dafeiji.ai.user.modules.property;

import com.bbz.tool.db.AbstractDataProviderWithUserName;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         2014-4-9 20:29
 *
 * 玩家常规属性的数据库实现
 */

class PropertyDataProvider extends AbstractDataProviderWithUserName<UserProperty>{
    private static final String TABLE_NAME = "property";

    /**
     * @param uname 玩家名称
     */
    public PropertyDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    @Override
    protected UserProperty decode( DBObject obj ){
        UserProperty property = new UserProperty();
        if( obj == null ) {
            return property;
        }
        property.setExp( (int) obj.get( "exp" ) );
        property.setGold( (int) obj.get( "gold" ) );
        property.setCash( (int) obj.get( "cash" ) );

        property.setAdult( (Boolean) obj.get( "isAdult" ) );
        property.setNickName( (String) obj.get( "nickName" ) );
        return property;
    }

    @Override
    protected DBObject encode( UserProperty property ){

        DBObject obj = new BasicDBObject();
        obj.put( "exp", property.getExp() );
        obj.put( "gold", property.getGold() );
        obj.put( "cash", property.getCash() );
        obj.put( "isAdult", property.isAdult() );
        obj.put( "nickName", property.getNickName() );
        return obj;
    }

}
