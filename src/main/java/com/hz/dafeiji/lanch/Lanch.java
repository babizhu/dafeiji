package com.hz.dafeiji.lanch;

import com.hz.dafeiji.cfg.CfgInit;
import com.hz.dafeiji.net.GameServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * user         LIUKUN
 * time         2014-4-3 14:38
 * 项目启动类
 */

public class Lanch{
    private static final Logger logger = LoggerFactory.getLogger( Lanch.class );

    public static void main( String[] args ) throws IOException{


        CfgInit.init();
        logger.info( "配置表读取完成" );
        GameServer server = new GameServer();
        server.run();


//        logger.debug( "服务器启动了" );

    }
}
