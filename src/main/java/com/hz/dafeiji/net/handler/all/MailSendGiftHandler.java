package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.mail.Mail;
import com.hz.dafeiji.ai.user.modules.mail.MailStore;
import com.hz.dafeiji.net.handler.IGameHandler;
import com.hz.util.Tools;

/**
 * Created by Valen on 2015/1/6.
 *
 */
@SuppressWarnings("UnusedDeclaration")
public class MailSendGiftHandler implements IGameHandler {

    @Override
    public void run(JSONObject request, JSONObject response, User user) {

        if(Tools.reqParamCheck(request, "u,i")){
            String receiver = request.getString("u");
            String award = request.getString("i");
            String sender = user.getUserBaseInfo().getUserName();

            String title = "送道具邮件的标题";
            String content = "您的好友["+sender+"]送给你了xxxx";

            Mail mail = new Mail(sender, receiver, title, content, award);
            MailStore.INSTANCE.addUserMail(mail);
        }else{
            throw new ClientException(ErrorCode.PARAMETER_ERROR, "MailSendGiftHandler缺少必要参数,传入参数:" + request.toString());
        }
    }
}
