package com.hz.dafeiji.ai.user.modules.mail;

import com.bbz.tool.db.IdentityObj;
import com.bbz.tool.identity.IdentityGen;
import com.bbz.tool.time.SystemTimer;

/**
 * Created by Valen on 2015/1/5.
 *
 * 标准邮件类, 收件人为单一用户
 */
@SuppressWarnings("UnusedDeclaration")
public class Mail implements IdentityObj {
    /**
     * 唯一标识
     */
    private long id;

    /**
     * 发件人用户名
     */
    private String sender;

    /**
     * 收件人用户名
     */
    private String receive;

    /**
     * 发送时间
     */
    private int sendTime;

    /**
     * 是否已读
     */
    private int isRead;

    /**
     * 邮件标题
     */
    private String title;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 附件道具列表
     */
    private String award;

    /**
     * 是否已收取附件
     */
    private int isAward;

    /**
     * 是否已删除
     */
    private int isDelete;

    public Mail(){}

    /**
     * 创建新邮件
     * @param sender  发件人用户名
     * @param receive  收件人用户名
     * @param title  邮件标题
     * @param content  邮件内容
     * @param award  附件道具，参考AwardModule类
     */
    public Mail(String sender, String receive, String title, String content, String award){
        this.id = IdentityGen.INSTANCE.incrementAndGet();
        this.sender = sender;
        this.receive = receive;
        this.sendTime = SystemTimer.currentTimeSecond();
        this.isRead = 0;
        this.title = title;
        this.content = content;
        this.award = award;
        this.isAward = 0;
        this.isDelete = 0;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public int getSendTime() {
        return sendTime;
    }

    public void setSendTime(int sendTime) {
        this.sendTime = sendTime;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getIsAward() {
        return isAward;
    }

    public void setIsAward(int isAward) {
        this.isAward = isAward;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
