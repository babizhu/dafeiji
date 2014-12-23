package com.hz.dafeiji.ai.user;


import com.hz.dafeiji.ai.user.modules.misc.usercounter.UserCounterModule;
import com.hz.dafeiji.ai.user.modules.player.UserBaseInfoModule;
import com.hz.dafeiji.ai.user.modules.property.PropertyModule;
import com.hz.dafeiji.ai.user.modules.recharge.RechargeModule;

/**
 * user         LIUKUN
 * time         2014-4-3 14:35
 * <p/>
 * user的模块管理器
 */

public class ModuleManager{


    private UserBaseInfoModule userBaseInfoModule;

    private PropertyModule propertyModule;

    private UserCounterModule userCounterModule;

    private RechargeModule rechargeModule;

    public ModuleManager( String uname ){
        userBaseInfoModule = new UserBaseInfoModule( uname );
        //propertyModule = new PropertyModule( uname );
        //userCounterModule = new UserCounterModule( uname );

    }

    public UserBaseInfoModule getUserBaseInfoModule(){
        return userBaseInfoModule;
    }

    public RechargeModule getRechargeModule(){

        return rechargeModule;
    }


    public UserCounterModule getUserCounterModule(){
        return userCounterModule;
    }

    public PropertyModule getPropertyModule(){
        return propertyModule;
    }
}
