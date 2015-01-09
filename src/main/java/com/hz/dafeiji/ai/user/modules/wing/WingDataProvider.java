package com.hz.dafeiji.ai.user.modules.wing;

import com.bbz.tool.db.AbstractDataProviderWithIdentity;
import com.hz.dafeiji.cfg.wing.WingTemplet;
import com.hz.dafeiji.cfg.wing.WingTempletCfg;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         2014-12-25 17:28
 */

public class WingDataProvider extends AbstractDataProviderWithIdentity<Wing>{

    private static final String TABLE_NAME = "wing";

    public WingDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    public static void main( String[] args ){
        System.out.println();
    }

    @Override
    protected Wing decode( DBObject object ){

        int templetId = (int) object.get( "templetId" );
        WingTemplet templet = WingTempletCfg.getWingTempletById( templetId );
        Wing wing = new Wing( (Long) object.get( "_id" ), templet );
        wing.setLevel( (int) object.get( "level" ) );
        wing.setCurrent( (boolean) object.get( "current" ) );

        return wing;
    }

    @Override
    protected DBObject encode( Wing wing ){
        DBObject obj = new BasicDBObject();
        obj.put( "_id", wing.getId() );
        obj.put( "level", wing.getLevel() );
        obj.put( "templetId", wing.getTemplet().getId() );
        obj.put( "current", wing.isCurrent() );

        return obj;
    }

    public void query(){


    }

    /**
     * 更新战机的当前字段
     *
     * @param wing 要更新的僚机
     */
    void updateCurrentPlaneField( Wing wing ){
        updateWithField( wing, "current", wing.isCurrent() );
    }


}
