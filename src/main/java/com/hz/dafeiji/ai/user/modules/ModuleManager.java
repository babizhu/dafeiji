package com.hz.dafeiji.ai.user.modules;


import com.hz.dafeiji.ai.user.modules.award.AwardModule;
import com.hz.dafeiji.ai.user.modules.mail.MailModule;
import com.hz.dafeiji.ai.user.modules.misc.usercounter.UserCounterModule;
import com.hz.dafeiji.ai.user.modules.plane.PlaneModule;
import com.hz.dafeiji.ai.user.modules.property.PropertyModule;
import com.hz.dafeiji.ai.user.modules.recharge.RechargeModule;

/**
 * user         LIUKUN
 * time         2014-4-3 14:35
 * <p/>
 * user的模块管理器
 */

public class ModuleManager{


    private PropertyModule propertyModule;

    private PlaneModule planeModule;

    private UserCounterModule userCounterModule;

    private RechargeModule rechargeModule;
    private AwardModule awardModule;

    private MailModule mailModule;


    public final String uname;

    public ModuleManager( String uname ){
        this.uname = uname;

        propertyModule = new PropertyModule( uname );
        planeModule = new PlaneModule( uname, this );
        awardModule = new AwardModule( uname, this );
        //userCounterModule = new UserCounterModule( uname );

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

        return awardModule;

    }

    public MailModule getMailModule(){
        if( mailModule != null ) {
            return mailModule;
        }
        mailModule = new MailModule( uname );
        return mailModule;
    }
}
