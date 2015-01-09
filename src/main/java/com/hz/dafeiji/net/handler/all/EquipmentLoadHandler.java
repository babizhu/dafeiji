package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.equipments.EquipmentModule;
import com.hz.dafeiji.net.handler.IGameHandler;
import com.hz.util.Tools;

/**
 * Created by Valen on 2015/1/9.
 * 用户穿戴装备接口
 */
public class EquipmentLoadHandler implements IGameHandler{

    @Override
    public void run(JSONObject request, JSONObject response, User user) {
        if(Tools.reqParamCheck(request, "i")){
            EquipmentModule module = user.getModuleManager().getEquipmentModule();

            module.loadEquip(request.getInteger("i"), user);
        }else{
            throw new ClientException(ErrorCode.PARAMETER_ERROR, "EquipmentLoadHandler缺少必要参数,传入参数:" + request.toString());
        }
    }
}
