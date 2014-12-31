package com.hz.dafeiji.ai.user.modules.plane;

import com.bbz.tool.db.AbstractDataProviderWithUserName;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         2014-4-9 20:29
 * <p/>
 * 用来记录玩家当前的出战飞机
 * <p/>
 * 这个方案没有采用，暂时留着
 */

class CurrentPlaneDataProvider extends AbstractDataProviderWithUserName<CurrentPlaneData>{
    private static final String TABLE_NAME = "currentPlane";

    /**
     * @param uname 玩家名称
     */
    public CurrentPlaneDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    @Override
    protected CurrentPlaneData decode( DBObject obj ){
        CurrentPlaneData property = new CurrentPlaneData();
        if( obj == null ) {
            //property.setCurrentPlaneId(  );
            return property;
        }
        //property.setExp( (int) obj.get( "exp" ) );
        property.setCurrentPlaneId( (long) obj.get( "currentPlaneId" ) );


        return property;
    }

    @Override
    protected DBObject encode( CurrentPlaneData property ){

        DBObject obj = new BasicDBObject();
        //obj.put( "exp", property.getExp() );

        obj.put( "currentPlaneId", property.getCurrentPlaneId() );


        return obj;
    }


}
