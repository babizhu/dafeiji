package com.hz.dafeiji.net.handler;

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

    /**
     * handler包的字符串，根据实际情况来调整，后面的"."不要丢了
     */
    public static final String HANDLER_PACKAGE = "com.hz.dafeiji.net.handler.";

    /**
     * 无需用户登录就能执行的handler
     */
//    private Map<MSG, INoLoginHandler> map1 = Maps.newHashMap();
//
//    /**
//     * 必须要用户登录才能执行的handler
//     */
//    private Map<MSG, IGameHandler> map2 = Maps.newHashMap();
//
//    HandlerManager(){
//
//        for( MSG msg : MSG.values() ) {
//            IHandler handler = buildHandler( msg );
//            if( handler instanceof INoLoginHandler ) {
//                map1.put( msg, (INoLoginHandler) handler );
//            } else {
//                map2.put( msg, (IGameHandler) handler );
//            }
//        }
//
////        System.out.println( "消息——句柄对应信息：" );
////        System.out.println( map1 );
////        System.out.println( map2 );
//    }
//
//    public static void main( String[] args ){
////        HandlerManager.INSTANCE.buildHandler( MSG.Login );
//        for( Object o : HandlerManager.INSTANCE.map1.values() ) {
//            System.out.println( o );
//        }
//    }
//
//    /**
//     * 通过MSG，利用反射生成相应的处理句柄的实例，所以MSG对应的类是存在如下映射关系：
//     * 例如MSG=MissionShow，那么相应的Handler类就是MissionShowHandler，所在的包由HANDLER_PACKAGE指定
//     *
//     * @param msg 通信包标示符，来自proto文件定义
//     * @return 相应的的handler类实例
//     */
//    private IHandler buildHandler( MSG msg ){
//
//        IHandler handler = null;
//        String className = String.format( "%s%sHandler", HANDLER_PACKAGE, StrUtil.firstCharacterToUpper( msg.toString() ) );
//
//        try {
//            handler = (IHandler) Class.forName( className ).newInstance();
//        } catch( ClassNotFoundException | InstantiationException | IllegalAccessException e ) {
//            e.printStackTrace();
//        }
//        return handler;
//    }
//
//    public IGameHandler getHandlerWithUser( MSG msg ){
//        return map2.get( msg );
//    }
//
//    public INoLoginHandler getHandlerWithoutUser( MSG msg ){
//        return map1.get( msg );
//    }
}
