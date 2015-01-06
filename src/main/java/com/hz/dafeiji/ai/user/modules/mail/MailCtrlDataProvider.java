package com.hz.dafeiji.ai.user.modules.mail;

import com.bbz.tool.db.AbstractDataProviderWithIdentity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Created by Valen on 2015/1/5
 * .
 */
public class MailCtrlDataProvider extends AbstractDataProviderWithIdentity<MailCtrl> {

    private static final String TABLE_NAME = "mailCtrl";
    private static final String FIELD_ID = "_id";
    private static final String FIELD_UNAME = "uname";
    private static final String FIELD_MAILID = "mailId";
    private static final String FIELD_READED = "readed";
    private static final String FIELD_RECEIVED = "received";
    private static final String FIELD_DELETED = "deleted";

    public MailCtrlDataProvider(String uname) {
        super(TABLE_NAME, uname);
    }

    @Override
    protected MailCtrl decode(DBObject obj) {
        MailCtrl mc = new MailCtrl();
        if(obj != null){
            mc.setId((long) obj.get(FIELD_ID));
            mc.setUname((String) obj.get(FIELD_UNAME));
            mc.setMailId((long) obj.get(FIELD_MAILID));
            mc.setReaded((int) obj.get(FIELD_READED));
            mc.setReceived((int) obj.get(FIELD_RECEIVED));
            mc.setDeleted((int) obj.get(FIELD_DELETED));
        }
        return mc;
    }

    @Override
    protected DBObject encode(MailCtrl ctrl) {
        DBObject obj = new BasicDBObject();
        obj.put(FIELD_ID, ctrl.getId());
        obj.put(FIELD_UNAME, ctrl.getUname());
        obj.put(FIELD_MAILID, ctrl.getMailId());
        obj.put(FIELD_READED, ctrl.getReaded());
        obj.put(FIELD_RECEIVED, ctrl.getReceived());
        obj.put(FIELD_DELETED, ctrl.getDeleted());
        return obj;
    }
}
