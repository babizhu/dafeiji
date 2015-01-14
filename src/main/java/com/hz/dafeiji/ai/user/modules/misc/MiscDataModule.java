package com.hz.dafeiji.ai.user.modules.misc;


import com.hz.dafeiji.ai.user.modules.ModuleManager;

import java.util.Map;

/**
 * user         LIUKUN
 * time         2014-5-3 11:39
 * 一个方便的存放杂项数据(数字和字符串)的地方，实用价值如何，需要在实际使用中确定
 */

public class MiscDataModule{
//    private static Logger logger = LoggerFactory.getLogger( MiscDataModule.class );

    private final Map<String, Object> data;
    private final MiscDataProvider db;

    public MiscDataModule( ModuleManager moduleManager  ){
        db = new MiscDataProvider( moduleManager.getUserName() );
        data = db.findOne();
    }


    /**
     * 返回内容，如果不存在返回""
     *
     * @param key  key
     * @param args 参数
     * @return 返回字符串结果
     */
    public String getString( MiscDataKey key, String... args ){
        String buildKey = key.buildKey( args );
        String ret = (String) data.get( buildKey );
        if( ret == null ) {
            ret = "";
        }
        return ret;
    }

    /**
     * 返回内容，如果不存在返回0
     *
     * @param key  key
     * @param args 参数
     * @return 返回整型值的结果
     */
    public int getInt( MiscDataKey key, String... args ){
        String buildKey = key.buildKey( args );
        Integer ret = (Integer) data.get( buildKey );
        if( ret == null ) {
            ret = 0;
        }
        return ret;
    }

    public boolean isMark( MiscDataKey key, String... args ){
        String buildKey = key.buildKey( args );
        Integer ret = (Integer) data.get( buildKey );
        if( ret == null || ret == 0) {
            return false;
        }
        return true;
    }

    public void putMark( MiscDataKey key, boolean value, String... args ){
        String buildKey = key.buildKey( args );
        int i = value ? 1 : 0;
        data.put( buildKey, i );
        //db.replace( data );不用整体更新
        db.updateWithField( buildKey, value );
    }

    public void put( MiscDataKey key, Object value, String... args ){
        String buildKey = key.buildKey( args );
        data.put( buildKey, value );
        //db.replace( data );不用整体更新
        db.updateWithField( buildKey, value );
    }

    /**
     * 目前是测试用的代码
     */
    @SuppressWarnings("UnusedDeclaration")
    void clear(){
        data.clear();
        db.remove();
    }
}
