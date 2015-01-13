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

    static final String LEVEL_FIELD = "level";
    static final String CURRENT_FIELD = "current";
    static final String TEMPLET_ID_FIELD = "templetId";
    static final String ID_FIELD = "_id";
    static final String QUALITY_FIELD = "quality";

    public WingDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    public static void main( String[] args ){
        System.out.println();
    }

    @Override
    protected Wing decode( DBObject object ){

        int templetId = (int) object.get( TEMPLET_ID_FIELD );
        WingTemplet templet = WingTempletCfg.getWingTempletById( templetId );
        Wing wing = new Wing( (Long) object.get( ID_FIELD ), templet );
        wing.setLevel( (int) object.get( LEVEL_FIELD ) );
        wing.setCurrent( (boolean) object.get( CURRENT_FIELD ) );
        wing.setQuality( (int)object.get( QUALITY_FIELD ) );

        return wing;
    }

    @Override
    protected DBObject encode( Wing wing ){
        DBObject obj = new BasicDBObject();
        obj.put( ID_FIELD, wing.getId() );
        obj.put( LEVEL_FIELD, wing.getLevel() );
        obj.put( TEMPLET_ID_FIELD, wing.getTemplet().getId() );
        obj.put( CURRENT_FIELD, wing.isCurrent() );
        obj.put( QUALITY_FIELD, wing.getQuality() );

        return obj;
    }

    public void query(){


    }


}
