package com.hz.dafeiji.ai.user.modules.mail;
import com.bbz.tool.db.MongoUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Valen on 2015/1/5.
 * 邮件系统类
 */
@SuppressWarnings("UnusedDeclaration")
public enum MailStore {
    INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(MailStore.class);

    private static final String TABLE_USER_MAIL = "mail";               //用户邮件表
    private static final String TABLE_GLOBAL_MAIL = "mailGlobal";     //针对全服用户的系统邮件表

    public static final String FIELD_ID = "_id";
    public static final String FIELD_SENDER = "sender";
    public static final String FIELD_RECEIVER = "receiver";
    public static final String FIELD_SENDTIME = "sendTime";
    public static final String FIELD_CONTENT = "content";
    public static final String FIELD_AWARD = "award";
    public static final String FIELD_ISSYSMAIL = "isSysMail";

    private ConcurrentHashMap<Long, Mail> userMail = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, Mail> globalMail = new ConcurrentHashMap<>();



    private DBCollection userConn = MongoUtil.INSTANCE.getDB().getCollection(TABLE_USER_MAIL);
    private DBCollection globalConn = MongoUtil.INSTANCE.getDB().getCollection(TABLE_GLOBAL_MAIL);

    /**
     * 初始化系统邮件仓库
     */
    public void init(){
        cacheTable(userConn, TABLE_USER_MAIL, userMail);
        cacheTable(globalConn, TABLE_GLOBAL_MAIL, globalMail);
        log.info("邮件系统启动成功");
    }

    /**
     * 添加用户邮件
     * @param mail Mail对象
     */
    public void addUserMail(Mail mail){
        if(validation(mail)){
            userMail.put(mail.getId(), mail);
            userConn.save(encode(mail, TABLE_USER_MAIL));
        }
    }

    /***
     * 添加全服系统邮件
     * @param mail Mail对象
     */
    public void addGlobalMail(Mail mail){
        if(validation(mail)){
            globalMail.put(mail.getId(), mail);
            globalConn.save(encode(mail, TABLE_GLOBAL_MAIL));
        }
    }



    /**
     * 根据收件人用户名获取玩家邮件
     * @param uname 用户名
     * @return List<Mail>
     */
    public List<Mail> getUserMailAll(String uname){
        List<Mail> list = new ArrayList<>();
        for(Map.Entry<Long, Mail> entry : userMail.entrySet()){
            Mail mail = entry.getValue();
            if(mail.getReceive().equals(uname) && mail.getIsSysMail() == 0){
                list.add(mail);
            }
        }
        return list;
    }

    /**
     * 根据邮件ID获取玩家邮件
     * @param id 邮件ID
     * @return Mail
     */
    public Mail getUserMailById(long id){
        return userMail.get(id);
    }

    /**
     * 根据邮件ID获取全服系统邮件
     * @param id 邮件ID
     * @return Mail
     */
    public Mail getGlobalMailById(long id){
        return globalMail.get(id);
    }

    /**
     * 获取系统邮件列表
     * @param uname 用户名
     * @return List<Mail>
     */
    public List<Mail> getSysMail(String uname){
        List<Mail> list = new ArrayList<>();
        for(Map.Entry<Long, Mail> entry : userMail.entrySet()){
            Mail mail = entry.getValue();
            if(mail.getReceive().equals(uname) && mail.getIsSysMail() > 0){
                list.add(mail);
            }
        }
        return list;
    }

    /**
     * 获取全服系统邮件列表
     * @return List<Mail>
     */
    public List<Mail> getGlobalSysMail(){
        List<Mail> list = new ArrayList<>();

        //再添加全服系统的邮件
        for(Map.Entry<Long, Mail> entry : globalMail.entrySet()){
            Mail mail = entry.getValue();
            list.add(mail);
        }
        return list;
    }



    /**
     * 用户删除邮件
     * @param mail 删除邮件ID
     */
    public void removeUserMail(Mail mail){
        userMail.remove(mail.getId());
        userConn.remove(encode(mail, TABLE_USER_MAIL));
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
            Mail mail = decode(cursor.next(), tableName);
            map.put(mail.getId(), mail);
        }
    }


    private Mail decode(DBObject obj, String tableName) {
        Mail mail = new Mail();
        mail.setId((long) obj.get(FIELD_ID));
        mail.setSendTime((int) obj.get(FIELD_SENDTIME));
        mail.setContent((String) obj.get(FIELD_CONTENT));
        mail.setAward((String) obj.get(FIELD_AWARD));

        if(tableName.equals(TABLE_USER_MAIL)){
            mail.setSender((String) obj.get(FIELD_SENDER));
            mail.setReceive((String) obj.get(FIELD_RECEIVER));
            mail.setIsSysMail((int) obj.get(FIELD_ISSYSMAIL));
        }
        return mail;
    }

    private DBObject encode(Mail mail, String tableName) {
        DBObject obj = new BasicDBObject(FIELD_ID, mail.getId());
        obj.put(FIELD_SENDTIME, mail.getSendTime());
        obj.put(FIELD_CONTENT, mail.getContent());
        obj.put(FIELD_AWARD, mail.getAward());

        if(tableName.equals(TABLE_USER_MAIL)) {
            obj.put(FIELD_SENDER, mail.getSender());
            obj.put(FIELD_RECEIVER, mail.getReceive());
            obj.put(FIELD_ISSYSMAIL, mail.getIsSysMail());
        }
        return obj;
    }

}
