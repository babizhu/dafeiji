package com.hz.dafeiji.ai.user.modules.stuff;

import com.bbz.tool.common.CountMap;
import com.bbz.tool.db.AbstractDataProviderWithUserName;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Map;

/**
 * user         LIUKUN
 * time         2014-4-9 20:29
 */

class StuffDataProvider extends AbstractDataProviderWithUserName<CountMap<Integer>>{
    private static final String TABLE_NAME = "stuff";

    /**
     * @param uname 玩家名称
     */
    public StuffDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    @Override
    protected CountMap<Integer> decode( DBObject obj ){
        CountMap<Integer> data = new CountMap<>();
        if( obj != null ) {
            for( String key : obj.keySet() ) {
                data.put( Integer.parseInt( key ), (int) obj.get( key ) );
            }
        }
        return data;
    }

    @Override
    protected DBObject encode( CountMap<Integer> data ){
        DBObject obj = new BasicDBObject();

        for( Map.Entry<Integer, Integer> entry : data.entrySet() ) {
            obj.put( entry.getKey()+"", entry.getValue() );
        }
        return obj;
    }


}
