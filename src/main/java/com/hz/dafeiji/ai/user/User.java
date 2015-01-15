package com.hz.dafeiji.ai.user;

import com.bbz.tool.time.SystemTimer;
import com.hz.dafeiji.ai.user.modules.ModuleManager;
import com.hz.dafeiji.ai.user.player.UserBaseInfo;

/**
 * user         LIUKUN
 * time         2014-4-9 18:02
 * <p/>
 * 用户类总管，其下包含各个功能模块
 * 注意各个功能模块之间不要形成循环引用，应该进行合适的功能拆分
 */

public class User{

    /**
     * 玩家最后一次活动离当前时间的间隔不超过ONLINE_TIME，则代表在线
     */
    private static final int ONLINE_TIME = 1000;

    private UserBaseInfo userBaseInfo;
    private final ModuleManager moduleManager;
    private String session;
    private int loginTime;
    private int activeTime;

//    public User( String uname ){
//        userBaseInfoModule = new UserBaseInfoModule( uname );
//        this.moduleManager = new ModuleManager( uname );
//    }

    public User( UserBaseInfo userBaseInfo ){
        this.userBaseInfo = userBaseInfo;
        this.moduleManager = new ModuleManager( userBaseInfo.getUserName() );
    }

    /**
     * 用于测试，可以不去检测玩家是否真的存在于数据库中
     *
     * @param uname
     */
    public User( String uname ){
        this.userBaseInfo = new UserBaseInfo();
        userBaseInfo.setUserName( uname );
        this.moduleManager = new ModuleManager( uname );
    }

    public ModuleManager getModuleManager(){
        return moduleManager;
    }


    public boolean isOnline(){
        return SystemTimer.currentTimeSecond() - activeTime < ONLINE_TIME;
    }

    public String getSesseion(){
        return session;
    }

    public void setSession( String session ){
        this.session = session;
    }

    public int getLoginTime(){
        return loginTime;
    }

    public void setLoginTime( int loginTime ){
        this.loginTime = loginTime;
    }

    public int getActiveTime(){
        return activeTime;
    }

    public void setActiveTimeNow(){
        this.activeTime = SystemTimer.currentTimeSecond();
    }

    public UserBaseInfo getUserBaseInfo(){
        return userBaseInfo;
    }

    /**
     * 删除玩家的所有信息，通常是测试用，这里要谨慎又谨慎
     */
    public void remove(){
        moduleManager.getPlaneModule().removeAll();
    }

}
