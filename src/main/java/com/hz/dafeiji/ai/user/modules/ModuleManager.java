package com.hz.dafeiji.ai.user.modules;


import com.hz.dafeiji.ai.user.modules.award.AwardModule;
import com.hz.dafeiji.ai.user.modules.equipments.EquipmentModule;
import com.hz.dafeiji.ai.user.modules.mail.MailModule;
import com.hz.dafeiji.ai.user.modules.misc.usercounter.UserCounterModule;
import com.hz.dafeiji.ai.user.modules.plane.PlaneModule;
import com.hz.dafeiji.ai.user.modules.property.PropertyModule;
import com.hz.dafeiji.ai.user.modules.recharge.RechargeModule;
import com.hz.dafeiji.ai.user.modules.wing.WingModule;

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
    private EquipmentModule equipmentModule;
    private WingModule wingModule;


    public String getUserName(){
        return uname;
    }

    private final String uname;

    public ModuleManager( String uname ){
        this.uname = uname;

        propertyModule = new PropertyModule( uname );
        awardModule = new AwardModule( uname, this );//注意先后顺序

        planeModule = new PlaneModule( uname, this );
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

    public EquipmentModule getEquipmentModule(){
        if(equipmentModule != null){
            return equipmentModule;
        }
        equipmentModule = new EquipmentModule(uname);
        return equipmentModule;
    }

    public WingModule getWingModule(){
        if(wingModule != null){
            return wingModule;
        }
        wingModule = new WingModule(this);
        return wingModule;
    }
}
