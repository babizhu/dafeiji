package com.hz.dafeiji.ai.user.modules.mail;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Valen on 2015/1/5.
 *
 */

@SuppressWarnings("UnusedDeclaration")
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
     * 用户删除系统邮件
     * @param id 系统邮件ID
     * @param uname 用户名
     */
    public void deleteSysMail(long id, String uname){
        Mail mail = MailStore.INSTANCE.getSysMailByMailId(id);
        if(mail != null){
            MailCtrl ctrl = getMailCtrlByMailId(id);
            if(ctrl != null){
                ctrl.setDeleted(1);
            }else{
                ctrl = new MailCtrl(uname, mail.getId(), 0, 0, 1);
                ctrlMail.put(ctrl.getId(), ctrl);
            }
            ctrlDb.replace(ctrl);
        }
    }

    /**
     * 用户删除邮件
     * @param id 邮件ID
     */
    public void deleteUserMail(long id){
        Mail mail = MailStore.INSTANCE.getUserMailByMailId(id);
        if(mail != null){
            mail.setIsDelete(1);
            MailStore.INSTANCE.updateUserMail(mail);
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
        return MailStore.INSTANCE.getSysMail(ctrlMail);
    }

    /**
     * 检查邮件控制器是否已经对某条系统邮件做了记录
     * @return MailCtrl
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

    /**
     * 转换发送给前端的Mail对象成JSONObject
     * @param mail Mail对象
     * @return JSONObject
     */
    private JSONObject toJSON(Mail mail){
        JSONObject obj = new JSONObject();
        obj.put("i", mail.getId());
        obj.put("s", mail.getSender());
        obj.put("t", mail.getTitle());
        obj.put("c", mail.getContent());
        obj.put("it", mail.getAward());
        return obj;
    }
}
