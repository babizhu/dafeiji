package com.hz.util;

import com.hz.dafeiji.cfg.equipment.EquipmentTemplet;
import com.hz.dafeiji.cfg.equipment.EquipmentTempletCfg;

/**
 * user         LIUKUN
 * time         2015-1-9 10:23
 */
public enum EquipmentType{
    MAIN(45){
        @Override
        float calc( int id){
            EquipmentTemplet templet = EquipmentTempletCfg.getEquipmentTempletById( 3 );
            return templet.getAttackAdd();
        }
    },
    FU(12){
        @Override
        float calc( int id){
            EquipmentTemplet templet = EquipmentTempletCfg.getEquipmentTempletById( 3 );
            return templet.getAttackAddUp();
        }
    };

    private int num;
    EquipmentType( int id ){
        this.num = id;
    }

    abstract float calc( int id);

    public static void main( String[] args ){
        EquipmentType type = FU;
        FU.calc(  3);
        type.calc( 34 );

//        Equipment
    }
}
