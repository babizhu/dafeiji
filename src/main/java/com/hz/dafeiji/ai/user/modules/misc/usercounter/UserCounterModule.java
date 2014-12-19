package com.hz.dafeiji.ai.user.modules.misc.usercounter;


import com.hz.dafeiji.ai.user.modules.misc.MiscDataKey;

/**
 * user         LIUKUN
 * time         2014/5/1 0001 11:45<br/>
 * <p/>
 * 午夜12点，所有的数据项都会清0，并非通过额外线程完成，而是通过一个附加的最后更新时间做对比<br/>
 *
 */
public class UserCounterModule{
//    private static Logger                   logger = LoggerFactory.getLogger( UserCounterModule.class );

    private final UserCounterRecord data;

    private final UserCounterDataProvider db;

    public UserCounterModule( String uname ){
        db = new UserCounterDataProvider( uname );
        data = db.findOne();
        if( !data.isToday() ) {
            clear();
        }
    }


    public int get( MiscDataKey key, String... args ){
        String buildKey = key.buildKey( args );
        if( !data.isToday() ) {
            clear();
            return 0;
        }

        return data.get( buildKey );
    }

    public void put( MiscDataKey key, int value, String... args ){
        if( !data.isToday() ) {
            clear();
        }

        String buildKey = key.buildKey( args );
        data.put( buildKey, value );
        db.update( buildKey, value );

    }

    public int add( MiscDataKey key, int change, String... args ){
        if( !data.isToday() ) {
            clear();
        }

        String buildKey = key.buildKey( args );
        int count = data.add( buildKey, change );
        db.update( buildKey, count );
        return count;
    }

    public void clear(){
        db.remove();
        data.clear();
    }

    /**
     * 语法糖，判断某个key今天是否已经被置位，例如有些奖励每天只能领取一次，就可以利用此功能进行判断
     *
     * @param key  key
     * @param args args
     * @return true：mark（对应数值为1）
     * false：NOT mark（对应数值为0）
     */
    public boolean isMark( MiscDataKey key, String... args ){
        int n = get( key, args );
        return n == 1;
    }

    /**
     * 设置某个key是否置位,联合#isMark()使用
     *
     * @param key    key
     * @param isMark true：置位   false：未置位
     * @param args   args
     */
    public void setMark( MiscDataKey key, boolean isMark, String... args ){
        int n = isMark ? 1 : 0;
        put( key, n, args );
    }

    @Override
    public String toString(){
        return "UserCounterModule{" +
                "data=" + data +
                '}';
    }

}
