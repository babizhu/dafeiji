package com.hz.dafeiji.script;


import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

/**
 * user         LIUKUN
 * time         2014-12-19 13:50
 * 可以远程执行代码
 */
public class RunScript{

    public static void main( String[] args ){
        //GroovyObject o = new Gro;
        String script = "import com.hz.dafeiji.ai.user.GameWorld;" +
                "public class TestCode {\n" +
                "\n" +
                "\tpublic String run() {\n" +
                "\t\treturn GameWorld.INSTANCE.getUserByName(\"eabdcdeqfd0\").getLoginTime();\n" +
                "\t}}";


//        System.out.println(GameWorld.INSTANCE.getOnlineUser() );
        new RunScript().run( script );


    }

    public void run( String script ){

        ClassLoader parent = ClassLoader.getSystemClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader( parent );
        Class gclass = loader.parseClass( script );

        try {
            GroovyObject groovyObject = (GroovyObject) gclass.newInstance();
            Object result = groovyObject.invokeMethod( "run", null );
            System.out.println( result );

        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            loader.clearCache();
        }
    }
}
