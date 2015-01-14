package com.hz.dafeiji.net.handler.admin;

import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.mail.Mail;
import com.hz.dafeiji.ai.user.modules.mail.MailStore;
import com.hz.dafeiji.net.handler.IGameHandler;
import com.hz.util.Tools;

/**
 * Created by Valen on 2015/1/14.
 * 给全服用户发送邮件接口
 */
public class MailSendGlobalHandler implements IGameHandler {
    @Override
    public void run(JSONObject request, JSONObject response, User user) {
        if(Tools.reqParamCheck(request, "c,a")){
            String content = request.getString("c");
            String award = request.getString("a");

            Mail mail = new Mail("", "", content, award, true);

            MailStore.INSTANCE.addGlobalMail(mail);
        }else{
            throw new ClientException(ErrorCode.PARAMETER_ERROR, "MailSendHandler缺少必要参数,传入参数:" + request.toString());
        }
    }
}
