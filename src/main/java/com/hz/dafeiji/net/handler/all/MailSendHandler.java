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
public class MailSendHandler implements IGameHandler {

    @Override
    public void run(JSONObject request, JSONObject response, User user) {
        if(Tools.reqParamCheck(request, "u,t,c")){
            String receive = request.getString("u");
            String title = request.getString("t");
            String content = request.getString("c");
            String sender = user.getUserBaseInfo().getUserName();

            Mail mail = new Mail(sender, receive, title, content, "");

            MailStore.INSTANCE.addUserMail(mail);
        }else{
            throw new ClientException(ErrorCode.PARAMETER_ERROR, "缺少必要参数,传入参数:" + request.toString());
        }
    }
}
