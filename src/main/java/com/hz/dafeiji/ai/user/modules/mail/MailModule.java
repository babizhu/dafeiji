package com.hz.dafeiji.ai.user.modules.mail;
import com.bbz.tool.common.Transform;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.modules.ModuleManager;
import com.hz.dafeiji.ai.user.modules.award.AwardModule;
import com.hz.dafeiji.ai.user.modules.misc.MiscDataKey;
import com.hz.dafeiji.ai.user.modules.misc.MiscDataModule;


import java.util.*;

/**
 * Created by Valen on 2015/1/5.
 *
 */

@SuppressWarnings("UnusedDeclaration")
public class MailModule {

    private static Comparator<Mail> mailComparator = new Comparator<Mail>() {
        @Override
        public int compare(Mail o1, Mail o2) {
            return o2.getSendTime() - o1.getSendTime();
        }
    };

    private final MiscDataModule miscDataModule;
    private final AwardModule awardModule;

    public MailModule(ModuleManager moduleManager) {
        miscDataModule = moduleManager.getMiscDataModule();
        awardModule = moduleManager.getAwardModule();
    }

    /**
     * 获取用户邮件列表
     * @param  uname 收件人用户名
     * @return List<Mail>
     */
    public List<Mail> getUserMails(String uname){
        return MailStore.INSTANCE.getUserMailAll(uname);
    }

    /**
     * 获取系统邮件列表
     * @return List<Mail>
     */
    public List<Mail> getSysMails(String uname){
        List<Mail> list = MailStore.INSTANCE.getSysMail(uname);
        List<Mail> globalMail = MailStore.INSTANCE.getGlobalSysMail();

        for(Mail global : globalMail){
            String mailId = global.getId() + "";
            if(!miscDataModule.isMark(MiscDataKey.GLOBAL_MAIL_AWARDED, mailId)){
                list.add(global);
            }
        }
        return list;
    }


    /**
     * 领取用户邮件
     * @param mailIds 要领取的邮件ID列表
     */
    public void awardUserMail(String uname, String mailIds){
        long[] ids = Transform.ArrayType.toLongs(mailIds);
        for(long id : ids){
            Mail mail = MailStore.INSTANCE.getUserMailById(id);
            if(mail != null){
                awardModule.addAward(mail.getAward(), "领取玩家邮件奖励,用户名:"+uname+",奖励:"+mail.getAward()+",说明:"+mail.getContent());

                MailStore.INSTANCE.removeUserMail(mail);
            }else{
                throw new ClientException(ErrorCode.MAIL_MAIL_NOT_FOUND,
                            "要领取的玩家邮件未找到,邮件ID:"+id+",玩家用户名:"+uname);
            }
        }
    }

    /**
     * 领取系统邮件
     * @param mailIds 要领取的邮件ID列表
     */
    public void awardSysMail(String uname, String mailIds){
        long[] ids = Transform.ArrayType.toLongs(mailIds);
        for(long id : ids){
            boolean isGlobalMail = false;
            Mail mail = MailStore.INSTANCE.getUserMailById(id);
            if(mail == null){
                mail = MailStore.INSTANCE.getGlobalMailById(id);
                isGlobalMail = true;
            }

            if(mail != null){
                awardModule.addAward(mail.getAward(), "领取系统邮件奖励,用户名:"+uname+",奖励:"+mail.getAward()+",说明:"+mail.getContent());

                if(!isGlobalMail){      //针对玩家的系统邮件
                    MailStore.INSTANCE.removeUserMail(mail);
                }else{                  //针对全服的系统邮件
                    miscDataModule.putMark(MiscDataKey.GLOBAL_MAIL_AWARDED, true, mail.getId()+"");
                }
            }else{
                throw new ClientException(ErrorCode.MAIL_MAIL_NOT_FOUND,
                        "要领取的系统邮件未找到,邮件ID:"+id+",玩家用户名:"+uname);
            }
        }
    }
}
