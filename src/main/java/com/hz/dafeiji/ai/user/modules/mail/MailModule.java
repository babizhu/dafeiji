package com.hz.dafeiji.ai.user.modules.mail;

import com.hz.dafeiji.ai.user.GameWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Valen on 2015/1/5.
 */

public class MailModule {
    final MailCtrlDataProvider ctrlDb;
    private Map<Long, MailCtrl> ctrlMail;

    public MailModule(String uname) {
        ctrlDb = new MailCtrlDataProvider(uname);
        ctrlMail = ctrlDb.getMapAll();
    }


    /**
     * 用户写新邮件
     * @param mail Mail对象
     */
    public void writeMail(Mail mail){
        MailStore.INSTANCE.addUserMail(mail);
    }

    /**
     * 用户读邮件
     * @param id 邮件ID
     * @param isSysMail 是否是系统邮件
     */
    public void readMail(long id, String uname, boolean isSysMail){
        Mail mail = isSysMail ? MailStore.INSTANCE.getSysMailByMailId(id) : MailStore.INSTANCE.getUserMailByMailId(id);
        if(mail != null){
            if(isSysMail){      //系统邮件的处理
                MailCtrl ctrl = getMailCtrlByMailId(id);
                if(ctrl != null){
                    ctrl.setReaded(1);
                }else{
                    ctrl = new MailCtrl(uname, mail.getId(), 1, 0, 0);
                    ctrlMail.put(ctrl.getId(), ctrl);
                }
                ctrlDb.replace(ctrl);
            }else{              //玩家邮件处理
                mail.setIsRead(1);  //修改读取状态
                MailStore.INSTANCE.updateUserMail(mail);
            }
        }
    }

    /**
     * 获取用户邮件列表
     * @param  uname 收件人用户名
     * @return List<Mail>
     */
    public List<Mail> getUserMails(String uname){
        return MailStore.INSTANCE.getUserMail(uname);
    }

    /**
     * 获取系统邮件列表
     * @return List<Mail>
     */
    public List<Mail> getSysMails(){
        List<Mail> list = MailStore.INSTANCE.getSysMail(ctrlMail);
        return list;
    }

    /**
     * 检查邮件控制器是否已经对某条系统邮件做了记录
     * @return
     */
    private MailCtrl getMailCtrlByMailId(long mailId){
        MailCtrl ctrl = null;
        for(Map.Entry<Long, MailCtrl> entry : ctrlMail.entrySet()){
            if(entry.getValue().getMailId() == mailId){
                ctrl = entry.getValue();
                break;
            }
        }
        return ctrl;
    }
}
