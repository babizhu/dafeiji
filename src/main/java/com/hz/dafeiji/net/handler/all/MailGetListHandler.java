package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.mail.Mail;
import com.hz.dafeiji.ai.user.modules.mail.MailModule;
import com.hz.dafeiji.net.handler.IGameHandler;

import java.util.List;

/**
 * Created by Valen on 2015/1/6.
 *
 */

@SuppressWarnings("UnusedDeclaration")
public class MailGetListHandler implements IGameHandler {
    @Override
    public void run(JSONObject request, JSONObject response, User user) {
        MailModule module = user.getModuleManager().getMailModule();

        String uname = user.getUserBaseInfo().getUserName();

        List<Mail> userMail = module.getUserMails(uname);
        List<Mail> sysMail = module.getSysMails(uname);

        response.put("um", toJSONArray(userMail));
        response.put("sm", toJSONArray(sysMail));
    }


    /**
     * 转换邮件列表成前端要用的JSONArray
     * @param list List<Mail>
     * @return JSONArray
     */
    private JSONArray toJSONArray(List<Mail> list){
        JSONArray arr = new JSONArray();
        for(Mail mail : list){
            JSONObject obj = new JSONObject();
            obj.put("i", mail.getId());
            obj.put("s", mail.getSender());
            obj.put("c", mail.getContent());
            obj.put("it", mail.getAward());
            arr.add(obj);
        }
        return arr;
    }
}
