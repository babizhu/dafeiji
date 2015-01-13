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
 * 用户分解装备
 */
public class EquipmentSplitHandler implements IGameHandler {

    @Override
    public void run(JSONObject request, JSONObject response, User user) {
        if(Tools.reqParamCheck(request, "i")){
            EquipmentModule module = user.getModuleManager().getEquipmentModule();

            module.splitEquip(request.getString("i"));

            response.put("e", user.getModuleManager().getPropertyModule().getEnergy());
        }else{
            throw new ClientException(ErrorCode.PARAMETER_ERROR, "EquipmentSplitHandler缺少必要参数,传入参数:" + request.toString());
        }
    }
}
