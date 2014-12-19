package com.hz.dafeiji.ai.user.modules.equipments;


import com.bbz.tool.db.IdentityObj;
import com.hz.dafeiji.cfg.equipment.EquipmentTemplet;
import com.hz.dafeiji.cfg.equipment.EquipmentTempletCfg;
import lombok.Data;

/**
 * user         LIUKUN
 * time         14-3-25 下午1:43
 */

@Data
public class Equipment implements IdentityObj{

    /**
     * 唯一标识
     */
    private final long id;

    private final EquipmentTemplet templet;
    private int level;

    public Equipment( long id, int templetId ){
        this.id = id;
        EquipmentTemplet et = EquipmentTempletCfg.getEquipmentTempletById( templetId );
        this.templet = et;
    }


    public void addLevel( int addValue ){
        level += addValue;
    }
}
