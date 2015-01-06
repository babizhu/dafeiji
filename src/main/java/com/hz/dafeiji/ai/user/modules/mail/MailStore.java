package com.hz.dafeiji.ai.user.modules.mail;

import com.bbz.tool.db.MongoUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Valen on 2015/1/5.
 * 邮件系统类
 */
@SuppressWarnings("UnusedDeclaration")
public enum MailStore {
    INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(MailStore.class);

    private static final String TABLE_USER_MAIL = "mail";
    private static final String TABLE_SYS_MAIL = "mailSys";

    private static final String FIELD_ID = "_id";
    private static final String FIELD_SENDER = "sender";
    private static final String FIELD_RECEIVER = "receiver";
    private static final String FIELD_SENDTIME = "sendTime";
    private static final String FIELD_ISREAD = "isRead";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_CONTENT = "content";
    private static final String FIELD_AWARD = "award";
    private static final String FIELD_ISAWARD = "isAward";
    private static final String FIELD_ISDELETE = "isDelete";

    private ConcurrentHashMap<Long, Mail> userMail = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, Mail> sysMail = new ConcurrentHashMap<>();

    private DBCollection userConn = MongoUtil.INSTANCE.getDB().getCollection(TABLE_USER_MAIL);
    private DBCollection sysConn = MongoUtil.INSTANCE.getDB().getCollection(TABLE_SYS_MAIL);

    /**
     * 初始化系统邮件仓库
     */
    public void init(){
        cacheTable(userConn, TABLE_USER_MAIL, userMail);
        cacheTable(sysConn, TABLE_SYS_MAIL, sysMail);
        log.info("邮件系统启动成功");
    }

    /**
     * 添加用户邮件
     * @param mail Mail对象
     */
    public void addUserMail(Mail mail){
        if(validation(mail)){
            userMail.put(mail.getId(), mail);
            userConn.save(encode(mail));
        }
    }

    /***
     * 添加系统邮件
     * @param mail Mail对象
     */
    public void addSysMail(Mail mail){
        if(validation(mail)){
            sysMail.put(mail.getId(), mail);
            sysConn.save(encode(mail));
        }
    }

    /**
     * 根据邮件ID获取用户邮件
     * @return Mail
     */
    public Mail getUserMailByMailId(long id){
        return userMail.get(id);
    }

    /**
     * 根据邮件ID获取用户邮件
     * @return Mail
     */
    public Mail getSysMailByMailId(long id){
        return sysMail.get(id);
    }

    /**
     * 根据收件人用户名获取邮件
     * @param uname 用户名
     * @return List<Mail>
     */
    public List<Mail> getUserMail(String uname){
        List<Mail> list = new ArrayList<>();
        for(Map.Entry<Long, Mail> entry : userMail.entrySet()){
            Mail mail = entry.getValue();
            if(mail.getReceive().equals(uname)){
                list.add(mail);
            }
        }
        return list;
    }

    /**
     * 获取系统邮件列表
     * @param ctrlMail 用户的系统邮件管理列表
     * @return List<Mail>
     */
    public List<Mail> getSysMail(Map<Long, MailCtrl> ctrlMail){
        List<Mail> list = new ArrayList<>();
        for(Map.Entry<Long, Mail> entry : sysMail.entrySet()){
            Mail mail = entry.getValue();
            boolean valideMail = true;

            if(mail.getIsDelete() == 0){
                for(Map.Entry<Long, MailCtrl> ctrlEntry : ctrlMail.entrySet()){
                    MailCtrl ctrl = ctrlEntry.getValue();
                    if(mail.getId() == ctrl.getMailId()){
                        if(ctrl.getDeleted() != 0){
                            valideMail = false;
                        }
                        break;
                    }
                }
            }else{
                valideMail = false;
            }

            if(valideMail){
                list.add(mail);
            }
        }
        return list;
    }


    /**
     * 修改玩家邮件
     * @param mail Mail
     */
    public void updateUserMail(Mail mail){
        userMail.put(mail.getId(), mail);
        userConn.save(encode(mail));
    }


    /**
     * 邮件有效性检查
     * @return boolean
     */
    private boolean validation(Mail mail){
        //以后补充
        return true;
    }

    /**
     * 读取数据库邮件数据缓存到JVM
     * @param conn DBCollection对象,用于持久化操作
     * @param tableName 要缓存的表名
     * @param map 缓存数据容器
     */
    private void cacheTable(DBCollection conn, String tableName, ConcurrentHashMap<Long, Mail> map){
        if(conn == null){
            conn = MongoUtil.INSTANCE.getDB().getCollection(tableName);
        }
        map.clear();

        DBCursor cursor = conn.find();
        while (cursor.hasNext()){
            Mail mail = decode(cursor.next());
            map.put(mail.getId(), mail);
        }
    }


    private Mail decode(DBObject obj) {
        Mail mail = new Mail();
        mail.setId((long) obj.get(FIELD_ID));
        mail.setSender((String) obj.get(FIELD_SENDER));
        mail.setReceive((String) obj.get(FIELD_RECEIVER));
        mail.setSendTime((int) obj.get(FIELD_SENDTIME));
        mail.setIsRead((int) obj.get(FIELD_ISREAD));
        mail.setTitle((String) obj.get(FIELD_TITLE));
        mail.setContent((String) obj.get(FIELD_CONTENT));
        mail.setAward((String) obj.get(FIELD_AWARD));
        mail.setIsAward((int) obj.get(FIELD_ISAWARD));
        mail.setIsDelete((int) obj.get(FIELD_ISDELETE));
        return mail;
    }

    private DBObject encode(Mail mail) {
        DBObject obj = new BasicDBObject(FIELD_ID, mail.getId());
        obj.put(FIELD_SENDER, mail.getSender());
        obj.put(FIELD_RECEIVER, mail.getReceive());
        obj.put(FIELD_SENDTIME, mail.getSendTime());
        obj.put(FIELD_ISREAD, mail.getIsRead());
        obj.put(FIELD_TITLE, mail.getTitle());
        obj.put(FIELD_CONTENT, mail.getContent());
        obj.put(FIELD_AWARD, mail.getAward());
        obj.put(FIELD_ISAWARD, mail.getIsAward());
        obj.put(FIELD_ISDELETE, mail.getIsDelete());
        return obj;
    }
}
