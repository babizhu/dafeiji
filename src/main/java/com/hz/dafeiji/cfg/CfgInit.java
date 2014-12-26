package com.hz.dafeiji.cfg;

import com.bbz.sanguo.cfg.equipment.EquipmentExpTempletCfg;
import com.bbz.sanguo.cfg.equipment.EquipmentQurlityTempletCfg;
import com.bbz.sanguo.cfg.equipment.EquipmentTempletCfg;
import com.bbz.sanguo.cfg.equipment.EquipmentTypeTempletCfg;
import com.bbz.sanguo.cfg.plane.PlaneQurlityTempletCfg;
import com.bbz.sanguo.cfg.plane.PlaneTempletCfg;
import com.bbz.sanguo.cfg.reward.BaseRewardTempletCfg;
import com.bbz.sanguo.cfg.skill.SikllTempletCfg;
import com.bbz.sanguo.cfg.wing.WingExpTempletCfg;
import com.bbz.sanguo.cfg.wing.WingQurlityTempletCfg;
import com.bbz.sanguo.cfg.wing.WingTempletCfg;


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
        PlaneQurlityTempletCfg.init();
        PlaneTempletCfg.init();
        BaseRewardTempletCfg.init();
        SikllTempletCfg.init();
        WingExpTempletCfg.init();
        WingQurlityTempletCfg.init();
        WingTempletCfg.init();

    }
}