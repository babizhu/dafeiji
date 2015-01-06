package com.hz.dafeiji.ai.user.modules.moneytree;


import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.modules.ModuleManager;
import com.hz.dafeiji.ai.user.modules.award.AwardModule;
import com.hz.dafeiji.ai.user.modules.misc.MiscDataKey;
import com.hz.dafeiji.ai.user.modules.misc.usercounter.UserCounterModule;

/**
 * user         LIUKUN
 * time         2014/5/4 0004 0:15
 * 摇钱树，采用UserCounter版本
 */

public class MoneyTreeSpecialModule{
    public static final String FUNC_NAME = MoneyTreeSpecialModule.class + "recharge";
    public final UserCounterModule userCounterModule;
    //    private final PropertyModule propModule;
    private final AwardModule awardModule;

    public MoneyTreeSpecialModule( ModuleManager moduleManager ){
        this.userCounterModule = moduleManager.getUserCounterModule();
        this.awardModule = null;
    }

    /**
     * @return 本次摇到的金币数量
     */
    public int run(){
        check();

        int money = 100;

        userCounterModule.add( MiscDataKey.MONEY_TREE, 1 );//今日摇钱次数加1

//        propModule.changeValue( UserPropertyType.CASH, money, FUNC_NAME );
        return money;
    }

    private void check(){
        int maxCount = 20;
        int runCountToday = userCounterModule.get( MiscDataKey.MONEY_TREE );
        if( runCountToday > maxCount ) {
            throw new ClientException( ErrorCode.MONEY_TREE_RUN_UPPER_LIMIT );
        }
    }
}
