package com.hz.dafeiji.ai.user.modules.equipments;

import com.bbz.tool.db.AbstractDataProviderWithIdentity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         14-3-26 下午2:38
 */

public class EquipmentDataProvider extends AbstractDataProviderWithIdentity<Equipment>{

    private static final String TABLE_NAME = "equipment";

    public EquipmentDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    public static void main( String[] args ){
        System.out.println();
    }

    @Override
    protected Equipment decode( DBObject object ){
        int templetId = ((int) object.get( "templetId" ));
        Equipment equipment = new Equipment( (long) object.get( "_id" ), templetId );
        equipment.setLevel( (int) object.get( "level" ) );

        return equipment;
    }

    @Override
    protected DBObject encode( Equipment equipment ){
        DBObject obj = new BasicDBObject( "_id", equipment.getId() );
        obj.put( "uname", getUname() );
        obj.put( "templetId", equipment.getTemplet().getId() );
        obj.put( "level", equipment.getLevel() );
        return obj;
    }

}