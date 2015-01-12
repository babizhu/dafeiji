package com.hz.dafeiji.net.handler.all;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.ai.user.modules.equipments.Equipment;
import com.hz.dafeiji.ai.user.modules.equipments.EquipmentModule;
import com.hz.dafeiji.net.handler.IGameHandler;

import java.util.List;

/**
 * Created by Valen on 2015/1/9.
 * 获取用户装备列表
 */
public class EquipmentGetListHandler implements IGameHandler {

    @Override
    public void run(JSONObject request, JSONObject response, User user) {
        EquipmentModule module = user.getModuleManager().getEquipmentModule();

        List<Equipment> list = module.getAllEquipments();
        JSONArray arr = new JSONArray();

        for(Equipment equip : list){
            JSONObject obj = new JSONObject();
            obj.put("i", equip.getId());
            obj.put("t", equip.getTemplet().getId());
            obj.put("l", equip.getLevel());
            obj.put("q", equip.getQuality());
            obj.put("s", equip.getLoaded());
            arr.add(obj);
        }

        response.put("l", arr);
    }
}
