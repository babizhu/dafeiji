package com.hz.dafeiji.ai.user.modules.mail;

import com.bbz.tool.db.IdentityObj;
import com.bbz.tool.identity.IdentityGen;

/**
 * Created by Valen on 2015/1/5.
 *
 * 用户邮件管理  主要用于记录针对系统邮件的已读和已领取附件的判断
 */

public class MailCtrl implements IdentityObj {

    /**
     * 记录ID
     */
    private long id;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 系统邮件ID
     */
    private long mailId;

    /**
     * 是否已读
     */
    private int readed;

    /**
     * 是否已领取道具
     */
    private int received;

    /**
     * 是否删除
     */
    private int deleted;


    public MailCtrl(){}

    public MailCtrl(String uname, long mailId, int readed, int received, int deleted){
        this.id = IdentityGen.INSTANCE.incrementAndGet();
        this.uname = uname;
        this.mailId = mailId;
        this.readed = readed;
        this.received = received;
        this.deleted = deleted;
    }


    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public long getMailId() {
        return mailId;
    }

    public void setMailId(long mailId) {
        this.mailId = mailId;
    }

    public int getReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    public int getReceived() {
        return received;
    }

    public void setReceived(int received) {
        this.received = received;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
