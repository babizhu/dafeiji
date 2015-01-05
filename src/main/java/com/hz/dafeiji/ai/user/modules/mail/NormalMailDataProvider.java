package com.hz.dafeiji.ai.user.modules.mail;

import com.bbz.tool.db.AbstractDataProviderWithIdentity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Created by Valen on 2015/1/5.
 */
public class NormalMailDataProvider extends AbstractDataProviderWithIdentity<NormalMail> {
    private static final String TABLE_NAME = "mail_normal";

    private static final String MAIL_ID = "_id";
    private static final String MAIL_SENDER = "sender";
    private static final String MAIL_RECEIVER = "receive";
    private static final String MAIL_SENDTIME = "sendTime";
    private static final String MAIL_ISREAD = "isRead";
    private static final String MAIL_TITLE = "title";
    private static final String MAIL_CONTENT = "content";
    private static final String MAIL_AWARD = "award";
    private static final String MAIL_ISAWARD = "isAward";
    private static final String MAIL_ISDELETE = "isDelete";


    public NormalMailDataProvider(String uname) {
        super(TABLE_NAME, uname);
    }

    @Override
    protected NormalMail decode(DBObject obj) {
        NormalMail mail = new NormalMail();
        mail.setId((long) obj.get(MAIL_ID));
        mail.setSender((String) obj.get(MAIL_SENDER));
        mail.setReceive((String) obj.get(MAIL_RECEIVER));
        mail.setSendTime((int) obj.get(MAIL_SENDTIME));
        mail.setIsRead((int) obj.get(MAIL_ISREAD));
        mail.setTitle((String) obj.get(MAIL_TITLE));
        mail.setContent((String) obj.get(MAIL_CONTENT));
        mail.setAward((String) obj.get(MAIL_AWARD));
        mail.setIsAward((int) obj.get(MAIL_ISAWARD));
        mail.setIsDelete((int) obj.get(MAIL_ISDELETE));
        return mail;
    }

    @Override
    protected DBObject encode(NormalMail mail) {
        DBObject obj = new BasicDBObject(MAIL_ID, mail.getId());
        obj.put(MAIL_SENDER, mail.getSender());
        obj.put(MAIL_RECEIVER, mail.getReceive());
        obj.put(MAIL_SENDTIME, mail.getSendTime());
        obj.put(MAIL_ISREAD, mail.getIsRead());
        obj.put(MAIL_TITLE, mail.getTitle());
        obj.put(MAIL_CONTENT, mail.getContent());
        obj.put(MAIL_AWARD, mail.getAward());
        obj.put(MAIL_ISAWARD, mail.getIsAward());
        obj.put(MAIL_ISDELETE, mail.getIsDelete());
        return obj;
    }
}
