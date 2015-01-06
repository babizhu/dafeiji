package com.hz.dafeiji.lanch;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * user         LIUKUN
 * time         2014-4-7 13:38
 * <p/>
 * 服务器的整体配置文件
 */

public class ServerCfg{

    /**
     * 监听ip
     */
    public static final String IP;

    /**
     * 监听端口
     */
    public static final int PORT;

    /**
     * 管理端口
     */
    public static final int GM_PORT;

    /**
     * 游戏区
     */
    public static final int SERVER_ID;

    static{
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = new BufferedInputStream( new FileInputStream( "resource/server.properties" ) );
            prop.load( in );

        } catch( IOException e ) {
            e.printStackTrace();
        } finally {
            if( in != null ) {
                try {
                    in.close();
                } catch( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
        IP = prop.getProperty( "ip" ).trim();
        PORT = Integer.parseInt( prop.getProperty( "port" ).trim() );
        GM_PORT = Integer.parseInt( prop.getProperty( "gmPort" ).trim() );
        SERVER_ID = Integer.parseInt( prop.getProperty( "serverId" ).trim() );

    }

    public static void main( String[] args ){
        System.out.println( ServerCfg.GM_PORT );
    }
}

