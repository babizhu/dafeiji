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
        //property.setExp( (int) obj.get( "exp" ) );
        property.setDiamond( (int) obj.get( "diamond" ) );
        property.setStrength( (int) obj.get( "strength" ) );
        property.setScoreWeek( (int) obj.get( "scoreWeek" ) );
        property.setScore( (int) obj.get( "score" ) );
        property.setPower( (int) obj.get( "power" ) );
        property.setVip( (int) obj.get( "vip" ) );


        return property;
    }

    @Override
    protected DBObject encode( UserProperty property ){

        DBObject obj = new BasicDBObject();
        //obj.put( "exp", property.getExp() );
        obj.put( "diamond", property.getDiamond() );
        obj.put( "strength", property.getStrength() );
        obj.put( "scoreWeek", property.getScoreWeek() );
        obj.put( "score", property.getScore() );
        obj.put( "strength", property.getStrength() );
        obj.put( "power", property.getPower() );
        obj.put( "vip", property.getVip() );


        return obj;
    }


}
