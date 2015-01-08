package com.hz.dafeiji.cfg.manual;

import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;
import com.hz.dafeiji.cfg.equipment.EquipmentQurlityTemplet;
import com.hz.dafeiji.cfg.equipment.EquipmentQurlityTempletCfg;
import com.hz.dafeiji.cfg.equipment.EquipmentTypeTemplet;
import com.hz.dafeiji.cfg.equipment.EquipmentTypeTempletCfg;
import com.hz.dafeiji.cfg.wing.WingQurlityTemplet;
import com.hz.dafeiji.cfg.wing.WingQurlityTempletCfg;
import com.hz.util.D;

import java.util.Map;

/**
 * Created by Valen on 2015/1/8.
 *
 *
 */
public class EquipExpCfg {
    //品阶，等级，升级所需经验
    private static final Table<Integer, Integer, Integer> table = TreeBasedTable.create();//HashBasedTable.create();

    public static final int MAX_QUALITY = 1000;

    static{
        EquipmentQurlityTempletCfg.init();
        EquipmentTypeTempletCfg.init();


        for( int i = 1; i < MAX_QUALITY; i++ ) {
            EquipmentQurlityTemplet wt = EquipmentQurlityTempletCfg.getEquipmentQurlityTempletById(i);
            if( wt != null ) {
                int energyExpend = wt.getEnergyExpend();        //基础能源
                int energyCoe = wt.getEnergyCoe();              //能源消耗系数

                for(int j=1; j<=wt.getMaxLv();j++){
                    //每一级的能源消耗= round（（基础能源+（当前等级-1）*（当前等级-1）*能源消耗系数）*装备类型系数，-1）* 升级XX常量
                    int needExp = (energyExpend + (j - 1) * (j - 1) * energyCoe);
                    table.put(i, j, needExp);
                }
            } else {
                break;
            }
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static void printAll(){
        for( int i = 1; i < MAX_QUALITY; i++ ) {
            Map<Integer, Integer> row = table.row( i );
            if( !row.isEmpty() ) {
                EquipmentQurlityTemplet wt = EquipmentQurlityTempletCfg.getEquipmentQurlityTempletById(i);
                for( int j = 1; j <= wt.getMaxLv(); j++ ) {
                    System.out.print( i + "," + j + "=" );
                    System.out.println( row.get( j ) );
                }
            } else {
                break;
            }
        }
    }

    /**
     * 获取装备升级所需经验
     * @param quality 装备品质
     * @param currentLevel 装备等级
     * @param type 装备类型
     * @return 所需经验
     */
    public static int getExp(int quality, int currentLevel, int type){
        int needExp = table.get(quality, currentLevel);
        EquipmentQurlityTemplet wt = EquipmentQurlityTempletCfg.getEquipmentQurlityTempletById(quality);
        EquipmentTypeTemplet tt = EquipmentTypeTempletCfg.getEquipmentTypeTempletById(type);
        if(currentLevel >= wt.getMaxLv()){
            return 0;
        }

        int energyConst = 10;                           //升级xx常量

        needExp = Math.round(needExp * tt.getRatioUp() / 10F) * 10 * energyConst;
        return needExp;
    }

    /**
     * 获取装备拆分获得能源
     * @param quality  装备品质
     * @param currentLevel  装备等级
     * @param type 装备类型
     * @return  获得能源
     */
    public static int getSplitExp(int quality, int currentLevel, int type){
        //拆分能源 = 每一级的能源消耗相加总和 * 拆分常量比例 + 拆分基础能源

        int costEnergy = 0;
        for(int i=1;i<=currentLevel - 1;i++){
            costEnergy += getExp(quality, i, type);
        }
        EquipmentQurlityTemplet wt = EquipmentQurlityTempletCfg.getEquipmentQurlityTempletById(quality);

        return Math.round((costEnergy * 0.1f + wt.getEnergyBasis()) / 10F) * 10;
    }


    public static void main( String[] args ){
        int type = 2;
        int level = 5;

        System.out.println("各品质等级"+level+"时需要的升级经验:");
        for(int i=1;i<=12;i++){
            System.out.println(getExp(i, level, type));
        }


        System.out.println("各品质等级"+level+"时获得的分解经验:");
        for(int i=1;i<=12;i++){
            System.out.println(getSplitExp(i, level, type));
        }
    }
}
