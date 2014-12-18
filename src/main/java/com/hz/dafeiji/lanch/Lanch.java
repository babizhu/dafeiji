package com.hz.dafeiji.lanch;

import com.hz.dafeiji.net.GameServer;

import java.io.IOException;

/**
 * user         LIUKUN
 * time         2014-4-3 14:38
 * 项目启动类
 */

public class Lanch{


    public static void main( String[] args ) throws IOException{

        GameServer server = new GameServer();
        server.run();


//        logger.debug( "服务器启动了" );

    }
}
