package com.hz.dafeiji.ai.user.modules.property;

import com.bbz.tool.db.AbstractDataProviderWithUserName;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * user         LIUKUN
 * time         2014-4-9 20:29
 * <p/>
 * 玩家常规属性的数据库实现
 */

class PropertyDataProvider extends AbstractDataProviderWithUserName<UserProperty>{
    private static final String TABLE_NAME = "property";

    static final String DIAMOND_FIELD = "diamond";
    static final String CASH_FIELD = "cash";
    static final String STRENGTH_FIELD = "strength";
    static final String SCORE_FIELD = "score";
    static final String SCORE_WEEK_FIELD = "scoreWeek";
    static final String VIP_FIELD = "vip";
    static final String POWER_FIELD = "power";

    /**
     * @param uname 玩家名称
     */
    public PropertyDataProvider( String uname ){
        super( TABLE_NAME, uname );
    }

    @Override
    protected UserProperty decode( DBObject obj ){
        UserProperty property = new UserProperty();
        if( obj == null ) {
            property.setStrength( new Strength() );
            replace( property );
            return property;
        }
        //property.setExp( (int) obj.get( "exp" ) );
        property.setDiamond( (int) obj.get( DIAMOND_FIELD ) );
        property.setCash( (int) obj.get( CASH_FIELD ) );
        int strengthValue = (int) obj.get( STRENGTH_FIELD );
        int lastCalcStrengthSecond = (int) obj.get( "lastCalcStrengthSecond" );
        Strength strength = new Strength( strengthValue, lastCalcStrengthSecond );
        property.setStrength( strength );
        property.setScoreWeek( (int) obj.get( SCORE_WEEK_FIELD ) );
        property.setScore( (int) obj.get( SCORE_FIELD ) );
        property.setPower( (int) obj.get( POWER_FIELD ) );
        property.setVip( (int) obj.get( VIP_FIELD ) );


        return property;
    }

    @Override
    protected DBObject encode( UserProperty property ){

        DBObject obj = new BasicDBObject();
        //obj.put( "exp", property.getExp() );
        obj.put( DIAMOND_FIELD, property.getDiamond() );
        obj.put( CASH_FIELD, property.getCash() );
        obj.put( STRENGTH_FIELD, property.getStrength().getStrength() );
        obj.put( SCORE_WEEK_FIELD, property.getScoreWeek() );
        obj.put( SCORE_FIELD, property.getScore() );

        obj.put( POWER_FIELD, property.getPower() );
        obj.put( VIP_FIELD, property.getVip() );
        obj.put( "lastCalcStrengthSecond", property.getStrength().getLastCalcStrengthSecond() );


        return obj;
    }


}
