package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.mail.MailModule;
import com.hz.dafeiji.net.handler.IGameHandler;
import com.hz.util.Tools;

/**
 * Created by Valen on 2015/1/6.
 *
 */
@SuppressWarnings("UnusedDeclaration")
public class MailDeleteHandler implements IGameHandler {
    @Override
    public void run(JSONObject request, JSONObject response, User user) {

        if(Tools.reqParamCheck(request, "sm,um")){
            String[] userMails = request.getString("um").split(",");
            String[] sysMails = request.getString("sm").split(",");
            MailModule module = user.getModuleManager().getMailModule();

            for(String s : userMails){
                if(!s.equals("")){
                    long mailId = Long.valueOf(s);
                    module.deleteUserMail(mailId);
                }
            }

            for(String s : sysMails){
                if(!s.equals("")){
                    long mailId = Long.valueOf(s);
                    module.deleteSysMail(mailId, user.getUserBaseInfo().getUserName());
                }
            }
        }else{
            throw new ClientException(ErrorCode.PARAMETER_ERROR, "MailDeleteHandler缺少必要参数,传入参数:" + request.toString());
        }
    }
}
