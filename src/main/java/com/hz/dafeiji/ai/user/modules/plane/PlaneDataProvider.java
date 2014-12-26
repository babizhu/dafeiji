package com.hz.dafeiji.ai.user.modules.plane;

import com.bbz.tool.db.AbstractDataProviderWithIdentity;
import com.hz.dafeiji.cfg.plane.PlaneTemplet;
import com.hz.dafeiji.cfg.plane.PlaneTempletCfg;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         2014-12-25 17:28
 */

public class PlaneDataProvider extends AbstractDataProviderWithIdentity<Plane>{

    private static final String TABLE_NAME = "plane";

    public PlaneDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    public static void main( String[] args ){
        System.out.println();
    }

    @Override
    protected Plane decode( DBObject object ){

        int templetId = (int) object.get( "templetId" );
        PlaneTemplet templet = PlaneTempletCfg.getPlaneTempletById( templetId );
        Plane plane = new Plane( (Long) object.get( "_id" ), templet );
//        hero.setName( (String) object.get( "name" ) );
//        Set<Equipment> equipments = Sets.newHashSet();
//        long[] arr = Transform.ArrayType.toLongs( (String) object.get( "equipmentS" ) );
//        EquipmentModule em = new EquipmentModule( "lk" );//TODO 需要考虑实际如何操作
//        for( long id : arr ) {
//            Equipment e = em.getEquipmentById( id );
//            equipments.add( e );
//        }
//
//        hero.setEquipments( equipments );
//        hero.setPosition( (int) object.get( "position" ) );
//
//
//        return hero;

        return null;
    }

    @Override
    protected DBObject encode( Plane plane ){
        DBObject obj = new BasicDBObject();
//        obj.put( "_id", hero.getId() );
//        //obj.put( "uname", getUname() );
//        obj.put( "name", hero.getName() );
//        obj.put( "position", hero.getPosition() );
//        String equipmentStr = "";
//        for( Equipment e : hero.getEquipments() ) {
//            equipmentStr += e.getId();
//            equipmentStr += ",";
//        }
//        obj.put( "equipmentS", equipmentStr );
//        obj.put( "templetId", hero.getTemplet().getId() );
        return obj;
    }


}
