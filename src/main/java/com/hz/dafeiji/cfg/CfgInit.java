package com.hz.dafeiji.cfg;

import com.hz.dafeiji.cfg.customs.MonsterTempletCfg;
import com.hz.dafeiji.cfg.equipment.EquipmentExpTempletCfg;
import com.hz.dafeiji.cfg.equipment.EquipmentQurlityTempletCfg;
import com.hz.dafeiji.cfg.equipment.EquipmentTypeTempletCfg;
import com.hz.dafeiji.cfg.equipment.EquipmentTempletCfg;
import com.hz.dafeiji.cfg.plane.PlaneQurlityTempletCfg;
import com.hz.dafeiji.cfg.plane.PlaneTempletCfg;
import com.hz.dafeiji.cfg.reward.ActShopTempletCfg;
import com.hz.dafeiji.cfg.reward.GoldShopTempletCfg;
import com.hz.dafeiji.cfg.reward.DiamondShopTempletCfg;
import com.hz.dafeiji.cfg.reward.BaseRewardTempletCfg;
import com.hz.dafeiji.cfg.reward.StuffTempletCfg;
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

       MonsterTempletCfg.init();
EquipmentExpTempletCfg.init();
EquipmentQurlityTempletCfg.init();
EquipmentTypeTempletCfg.init();
EquipmentTempletCfg.init();
PlaneQurlityTempletCfg.init();
PlaneTempletCfg.init();
ActShopTempletCfg.init();
GoldShopTempletCfg.init();
DiamondShopTempletCfg.init();
BaseRewardTempletCfg.init();
StuffTempletCfg.init();
SikllTempletCfg.init();
WingExpTempletCfg.init();
WingQurlityTempletCfg.init();
WingTempletCfg.init();

    }
}