package com.hz.dafeiji.net.handler;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * user         LIUKUN
 * time         2014-5-29 11:37
 * <p/>
 * 通过protobuf中定义的MSG,来获取相应的handler，这里就必须有个规则：<br/>
 * MSG里面的消息定义，然后加上Handler就是相应的类，举例如下<br/>
 * MSG:Login====>LoginHandler.java
 * <p/>
 * MSG里面的首字母推荐大写，不过不是硬性规定
 */

public enum HandlerManager{
    INSTANCE;
    private Logger logger = LoggerFactory.getLogger( HandlerManager.class );

    /**
     * handler包的字符串，根据实际情况来调整，后面的"."不要丢了
     */
    public static final String HANDLER_PACKAGE = "com.hz.dafeiji.net.handler.all";

    /**
     * handler
     */
    private Map<String, IGameHandler> map = Maps.newHashMap();

//    /**
//     * 必须要用户登录才能执行的handler
//     */
//    private Map<MSG, IGameHandler> map2 = Maps.newHashMap();

    HandlerManager(){


        List<Class<?>> list = PackageUtil.getClasses( HANDLER_PACKAGE );
        //int max = 1000;
        //final EventBase[] packets = new EventBase[max];// 不存在0号包

        // 生成所有包的实例数组，供后面调用
        for( Class<?> c : list ) {

//           logger.info( c.getName() );
            try {
                IGameHandler handler = (IGameHandler) c.newInstance();
                String handlerName = handler.getClass().getSimpleName();
                if( map.containsKey( handlerName ) ) {
                    logger.error( handlerName + "重复了" );
                    return;
                }
                map.put( buildHandlerName( handlerName ), handler );
            } catch( InstantiationException | IllegalAccessException e ) {
                e.printStackTrace();
                logger.error( e.toString() );
            }


//                // System.out.println( c.getName() + " ：" + p1.getPacketNo() );
//
//                int packetNo = p1.getEventId();
//                EventBase ip = packets[packetNo];
//                if( ip == null ) {
//                    packets[packetNo] = p1;
//
//                } else {
//                    System.out.println( packetNo + " 重复了" );
//                }
        }
//        }
//        for( MSG msg : MSG.values() ) {
//            IHandler handler = buildHandler( msg );
//            if( handler instanceof INoLoginHandler ) {
//                map1.put( msg, (INoLoginHandler) handler );
//            } else {
//                map2.put( msg, (IGameHandler) handler );
//            }
//        }

//        System.out.println( "消息——句柄对应信息：" );
//        System.out.println( map1 );
//        System.out.println( map2 );
    }

    /**
     * 通过类似UserLoginHandler的类名称，得到相应的索引(key)字符串“userlogin”，
     * 生成map后方便前端调用
     *
     * @param handlerName 类名称（LoginHandler）
     * @return 相应的字符串
     */
    private String buildHandlerName( String handlerName ){
        String name = handlerName.substring( 0, handlerName.length() - "Handler".length() );//去掉结尾的"Handler"
        return name.toLowerCase();

    }


    public IGameHandler getHandler( String msg ){
        return map.get( msg );
    }


    public static void main( String[] args ){
//        HandlerManager.INSTANCE.buildHandler( MSG.Login );
//        for( Object o : HandlerManager.INSTANCE.map1.values() ) {
//            System.out.println( o );
//        }

        for( Map.Entry<String, IGameHandler> entry : HandlerManager.INSTANCE.map.entrySet() ) {
            System.out.println( entry.getKey() + ":" + entry.getValue().getClass().getSimpleName() );
        }

        HandlerManager.INSTANCE.getHandler( "login" ).run( null, null, null );
    }

}
