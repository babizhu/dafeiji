package com.hz.dafeiji.ai.user.modules.award;

import com.google.common.collect.Lists;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.modules.property.PropertyModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-12-17 16:43
 * <p/>
 * 通用的所有的玩家属性发放，都从这里进行，包括经验，金币，道具等等
 */

public class AwardModule{

    private static final Logger logger = LoggerFactory.getLogger( AwardModule.class );
    private final PropertyModule propertyModule;
    private final String uname;

    public AwardModule( String uname, PropertyModule propertyModule ){
        this.uname = uname;
        this.propertyModule = propertyModule;
    }

    /**
     * 给玩家发放物品或者属性
     *
     * @param awardStr 发放道具的字符串,例如100021,2,100938,200。即ID为100021的物品发放2个，道具ID为100938的物品发放200个
     */
    public void addAward( String awardStr ){

    }


    /**
     * 扣除玩家的物品或者属性
     *
     * @param reduceStr 扣除道具的字符串,例如100021,2,100938,200,即ID为100021的物品扣除2个，道具ID为100938的物品扣除200个
     * @param func      导致属性变化的原因
     */
    public void reduceAward( String reduceStr, String func ){
        List<PropItem> props = transform( reduceStr );
        if( !checkEnough( props ) ) {
            throw new ClientException( ErrorCode.AWARD_NOT_ENOUGH );
        }

        for( PropItem prop : props ) {
            if( prop.count < 0 ) {
                throw new ClientException( ErrorCode.AWARD_REDUCE_COUNT_ILLEGAL, prop.propId + "," + prop.count );
            }

            int count = -prop.count;//因为是扣除，因此把数量全转为负值

            int remainCount = 0;//变化之后的值，log用
            switch( prop.propId ) {
                case 500001:
                    remainCount = propertyModule.changeCash( prop.count );
                    break;
                case 500002:
                    remainCount = propertyModule.changeDiamond( prop.count );
                    break;
                case 500007:
                    remainCount = propertyModule.changeStrength( prop.count );
                    break;
                default:
                    throw new ClientException( ErrorCode.AWARD_REDUCE_COUNT_ILLEGAL, prop.propId + "," + prop.count );


            }
            buildLog( prop.propId, count, remainCount, func );

        }
    }

    /**
     * 检测各种属性是否足够
     * 只要一个不满足，即返回false
     * <p/>
     *
     * @param props 扣除道具的字符串,例如100021,2,100938,200
     *              即ID为100021的物品发放2个，道具ID为100938的物品发放200个
     */
    private boolean checkEnough( List<PropItem> props ){
        for( PropItem prop : props ) {
            if( prop.count < 0 ) {
                throw new ClientException( ErrorCode.AWARD_REDUCE_COUNT_ILLEGAL, prop.propId + "," + prop.count );
            }
            switch( prop.propId ) {
                case 500001:
                    if( propertyModule.getCash() < prop.count ) {
                        return false;
                    }
                    break;
                case 500002:
                    if( propertyModule.getDiamond() < prop.count ) {
                        return false;
                    }
                    break;
                case 500007:
                    if( propertyModule.getStrength() < prop.count ) {
                        return false;
                    }
                    break;
                default:
                    throw new ClientException( ErrorCode.AWARD_REDUCE_COUNT_ILLEGAL, prop.propId + "," + prop.count );


            }


        }
        return true;
    }

    /**
     * 把道具字符串转换为list，方便处理
     *
     * @param propStr 相应的道具字符串,例如100021,2,100938,200
     * @return 相应的list
     */
    private List<PropItem> transform( String propStr ){
        List<PropItem> list = Lists.newArrayList();
        String[] props = propStr.split( "," );
        for( int i = 0; i < props.length; ) {

            int propId = Integer.parseInt( props[i++] );
            int count = Integer.parseInt( props[i++] );

            list.add( new PropItem( propId, count ) );
        }
        return list;
    }

    /**
     * 构造关键数据的日志文件
     *
     * @param propId   道具id
     * @param change   改动的数值
     * @param current  改动后的当前数值
     * @param funcName 改动函数
     */
    private void buildLog( int propId, int change, int current, String funcName ){
        //{}依次为 用户名，物品类型，变化值，当前值，调用的方法名
        logger.info( "{},{},{},{},{}", uname, propId, change, current, funcName );
    }

}
