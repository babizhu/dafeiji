package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.equipments.EquipmentModule;
import com.hz.dafeiji.net.handler.IGameHandler;
import com.hz.util.Tools;

/**
 * Created by Valen on 2015/1/8.
 * 添加用户装备
 */
public class EquipmentAddHandler implements IGameHandler {
    @Override
    public void run(JSONObject request, JSONObject response, User user) {
        if(Tools.reqParamCheck(request, "i")){
            String ids = request.getString("i");
            EquipmentModule module = user.getModuleManager().getEquipmentModule();

            module.addAll(ids);

        }else{
            throw new ClientException(ErrorCode.PARAMETER_ERROR, "EquipmentAddHandler缺少必要参数,传入参数:" + request.toString());
        }
    }
}
