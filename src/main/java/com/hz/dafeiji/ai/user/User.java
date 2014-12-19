package com.hz.dafeiji.ai.user;

/**
 * user         LIUKUN
 * time         2014-4-9 18:02
 */

public class User{

    private final ModuleManager   moduleManager;

    public User( String uname ){
        this.moduleManager = new ModuleManager( uname );
    }

    public ModuleManager getModuleManager(){
        return moduleManager;
    }


    boolean isOnline(){
        return true;
    }
}
