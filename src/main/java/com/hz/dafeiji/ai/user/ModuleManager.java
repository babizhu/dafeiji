package com.hz.dafeiji.ai.user;


import com.hz.dafeiji.ai.user.modules.award.AwardModule;
import com.hz.dafeiji.ai.user.modules.misc.usercounter.UserCounterModule;
import com.hz.dafeiji.ai.user.modules.plane.PlaneModule;
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

    private PlaneModule planeModule;

    private UserCounterModule userCounterModule;

    private RechargeModule rechargeModule;
    private AwardModule awardModule;

    public final String uname;

    public ModuleManager( String uname ){
        this.uname = uname;
        userBaseInfoModule = new UserBaseInfoModule( uname );
        propertyModule = new PropertyModule( uname );
        planeModule = new PlaneModule( uname );
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

    public PlaneModule getPlaneModule(){
        return planeModule;
    }

    public AwardModule getAwardModule(){
        if( awardModule != null ) {
            return awardModule;
        }
        return new AwardModule( uname, propertyModule );
    }
}
