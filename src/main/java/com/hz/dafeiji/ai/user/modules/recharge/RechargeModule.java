package com.hz.dafeiji.ai.user.modules.recharge;


import com.hz.dafeiji.ai.user.modules.ModuleManager;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-4-9 17:52
 * 充值管理器
 */

public class RechargeModule{

    public static final String FUNC_NAME = RechargeModule.class + "recharge";
    // private final PropertyModule propModule;
    // private final RechargeDataProvider db;
    private List<RechargeRecord> rechargeRecordList;
    /**
     * 玩家的充值金额(RMB)
     */
    private int money;

    public RechargeModule( ModuleManager moduleManager ){

//        String funcName = getClass().getName() + "recharge";
//        this.moduleManager = moduleManager;
//        propModule = moduleManager.getPropertyModule();
//        db = new RechargeDataProvider( moduleManager.getUserName() );
//        rechargeRecordList = db.getListAll();
//        for( RechargeRecord record : rechargeRecordList ) {
//            money += record.getMoney();
//
//        }
    }

    /**
     * 充值
     *
     * @param money 金钱
     */
    public void recharge( int money ){
//        long id = IdentityGen.INSTANCE.incrementAndGet();
//        RechargeRecord record = new RechargeRecord( id, money );
//        rechargeRecordList.add( record );
//        db.add( record );
//
//        int gold = money;//应该有个兑换比例
//
//        propModule.changeValue( UserPropertyType.GOLD, gold, FUNC_NAME );
//        this.money += money;
    }

    public int getMoney(){
        return money;
    }

}
