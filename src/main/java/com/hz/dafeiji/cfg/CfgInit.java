package com.hz.dafeiji.cfg;

import com.hz.dafeiji.cfg.equipment.EquipmentExpTempletCfg;
import com.hz.dafeiji.cfg.equipment.EquipmentQurlityTempletCfg;
import com.hz.dafeiji.cfg.equipment.EquipmentTempletCfg;
import com.hz.dafeiji.cfg.equipment.EquipmentTypeTempletCfg;
import com.hz.dafeiji.cfg.misc.PropIdTempletCfg;
import com.hz.dafeiji.cfg.plane.PlaneQurlityTempletCfg;
import com.hz.dafeiji.cfg.plane.PlaneTempletCfg;
import com.hz.dafeiji.cfg.reward.BaseRewardTempletCfg;
import com.hz.dafeiji.cfg.skill.SikllTempletCfg;
import com.hz.dafeiji.cfg.wing.WingExpTempletCfg;
import com.hz.dafeiji.cfg.wing.WingQurlityTempletCfg;
import com.hz.dafeiji.cfg.wing.WingTempletCfg;


/**
 * user         LIUKUN
 * time         2014-4-25 13:52
 * 初始化所有的配置文件
 */

public class CfgInit{
    public static void init(){

        EquipmentExpTempletCfg.init();
        EquipmentQurlityTempletCfg.init();
        EquipmentTypeTempletCfg.init();
        EquipmentTempletCfg.init();
        PropIdTempletCfg.init();
        PlaneQurlityTempletCfg.init();
        PlaneTempletCfg.init();
        BaseRewardTempletCfg.init();
        SikllTempletCfg.init();
        WingExpTempletCfg.init();
        WingQurlityTempletCfg.init();
        WingTempletCfg.init();

    }
}