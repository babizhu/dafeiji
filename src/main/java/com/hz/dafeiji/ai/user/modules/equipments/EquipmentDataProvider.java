package com.hz.dafeiji.ai.user.modules.equipments;

import com.bbz.tool.db.AbstractDataProviderWithIdentity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         14-3-26 下午2:38
 */

public class EquipmentDataProvider extends AbstractDataProviderWithIdentity<Equipment>{

    public static final String TABLE_NAME = "equipment";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_TEMPLETID = "templetId";
    public static final String FIELD_LEVEL = "level";
    public static final String FIELD_QUALITY = "quality";
    public static final String FIELD_LOADED = "loaded";
    public static final String FIELD_ISDELETE = "isDelete";
    public static final String FIELD_ISLOCKED = "isLocked";

    public EquipmentDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }


    @Override
    protected Equipment decode( DBObject object ){
        long id = (long) object.get(FIELD_ID);
        int templeteId = (int) object.get(FIELD_TEMPLETID);
        int level = (int) object.get(FIELD_LEVEL);
        int quality = (int) object.get(FIELD_QUALITY);
        int loaded = (int) object.get(FIELD_LOADED);
        int isDelete = (int) object.get(FIELD_ISDELETE);
        int isLocked = (int) object.get(FIELD_ISLOCKED);
        return new Equipment(id, templeteId, level, quality, loaded, isDelete, isLocked);
    }

    @Override
    protected DBObject encode(Equipment equip){
        DBObject obj = new BasicDBObject(FIELD_ID, equip.getId());
        obj.put(FIELD_TEMPLETID, equip.getTemplet().getId());
        obj.put(FIELD_LEVEL, equip.getLevel());
        obj.put(FIELD_QUALITY, equip.getQuality());
        obj.put(FIELD_LOADED, equip.getLoaded());
        obj.put(FIELD_ISDELETE, equip.getIsDelete());
        obj.put(FIELD_ISLOCKED, equip.getLocked());
        return obj;
    }

}