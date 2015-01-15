package com.hz.dafeiji.ai.user.modules.award;

import com.google.common.collect.Lists;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.modules.ModuleManager;
import com.hz.dafeiji.ai.user.modules.property.PropertyModule;
import com.hz.dafeiji.ai.user.modules.stuff.StuffModule;
import com.hz.dafeiji.cfg.define.Define;
import com.hz.dafeiji.cfg.define.PropIdDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * user         LIUKUN
 * time         2014-12-17 16:43
 * <p/>
 * 通用的所有的玩家属性发放，都从这里进行，包括经验，金币，道具，飞机等等
 * 此类似乎不可避免会和其他package形成循环引用，比如购买飞机，这里需要引用plan的pacakge，而购买飞机需要扣除金币
 * 又需要在plan的pacakge中引用本类
 */

public class AwardModule{

    private static final Logger AWARD_LOG = LoggerFactory.getLogger( "AWARD_LOG" );
    private static final Logger logger = LoggerFactory.getLogger( AwardModule.class );

    private final PropertyModule propertyModule;
    private final StuffModule stuffModule;
    private final String uname;
    private final ModuleManager moduleManager;

    public AwardModule( String uname, ModuleManager moduleManager ){
        this.uname = uname;
        //this.propertyModule = propertyModule;
        //this.planeModule = planeModule;
        this.moduleManager = moduleManager;
        propertyModule = moduleManager.getPropertyModule();
        stuffModule = moduleManager.getStuffModule();
    }

    /**
     * 给玩家发放物品或者属性
     *
     * @param awardStr 发放道具的字符串,例如100021,2,100938,200。即ID为100021的物品发放2个，道具ID为100938的物品发放200个
     * @param func     导致属性变化的原因，方便后台查询
     */
    public void addAward( String awardStr, String func ){
        List<PropItem> props = transform( awardStr );
        changeValue( props, func );
    }


    /**
     * 扣除玩家的物品或者属性,如果输入为0，则直接返回，意味着无需扣除任何东西
     *
     * @param reduceStr 扣除道具的字符串,例如100021,2,100938,200,即ID为100021的物品扣除2个，道具ID为100938的物品扣除200个
     * @param func      导致属性变化的原因
     */
    public void reduceAward( String reduceStr, String func ){
        if( reduceStr.equals( "0" ) ) {
            return;
        }
        List<PropItem> props = transform( reduceStr );
        ErrorCode errorCode = checkEnough( props );
        if( !errorCode.isSuccess() ) {
            throw new ClientException( errorCode );
        }

        for( PropItem prop : props ) {
            if( prop.count < 0 ) {
                throw new ClientException( ErrorCode.AWARD_REDUCE_COUNT_ILLEGAL, prop.propId + "," + prop.count );
            }

            prop.count = -prop.count;//因为是扣除，因此把数量全转为负值
        }
        changeValue( props, func );
    }

    private void changeValue( List<PropItem> props, String func ){
        for( PropItem prop : props ) {

            int remainCount = 0;//变化之后的值，log用
            int propId = prop.propId;
            if( propId == PropIdDefine.CASH_JIN_BI ) {
                remainCount = propertyModule.changeCash( prop.count );
            } else if( propId == PropIdDefine.CASH_ZUAN_SHI ) {
                remainCount = propertyModule.changeDiamond( prop.count );
            } else if( propId == PropIdDefine.XING_DONG_LI ) {
                remainCount = propertyModule.changeStrength( prop.count );
            } else if( propId == PropIdDefine.NENG_YUAN ) {      //能源获取
                remainCount = propertyModule.changeEnergy( prop.count );
            } else if( propId >= Define.ID_STUFF_MIN && propId <= Define.ID_STUFF_MAX ) {//道具材料
                remainCount = stuffModule.changeStuff( propId, prop.count );

            } else {
                throw new ClientException( ErrorCode.AWARD_PROP_NOT_FOUND, prop.propId + "," + prop.count );
            }
            buildLog( prop.propId, prop.count, remainCount, func );

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
    private ErrorCode checkEnough( List<PropItem> props ){
        for( PropItem prop : props ) {
            if( prop.count < 0 ) {
                throw new ClientException( ErrorCode.AWARD_REDUCE_COUNT_ILLEGAL, prop.propId + "," + prop.count );
            }
            int propId = prop.propId;
            if( propId == PropIdDefine.CASH_JIN_BI ) {
                if( propertyModule.getCash() < prop.count ) {
                    return ErrorCode.USER_CASH_NOT_ENOUGH;
                }
            } else if( propId == PropIdDefine.CASH_ZUAN_SHI ) {
                if( propertyModule.getDiamond() < prop.count ) {
                    return ErrorCode.USER_DIAMOND_NOT_ENOUGH;
                }
            } else if( propId == PropIdDefine.XING_DONG_LI ) {
                if( propertyModule.getStrength() < prop.count ) {
                    return ErrorCode.USER_STRENGTH_NOT_ENOUGH;
                }
            } else if( propId == PropIdDefine.NENG_YUAN ) {
                if( propertyModule.getEnergy() < prop.count ) {
                    return ErrorCode.USER_ENERGY_NOT_ENOUGH;
                }
            }  else if( propId >= Define.ID_STUFF_MIN && propId <= Define.ID_STUFF_MAX ) {//道具材料
                if( !stuffModule.isEnough( propId, prop.count ) ){
                    return ErrorCode.STUFF_NOT_ENOUGH;
                }

        } else {
                throw new ClientException( ErrorCode.AWARD_PROP_NOT_FOUND, prop.propId + "," + prop.count );
            }
        }
        return ErrorCode.SUCCESS;
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
        AWARD_LOG.info( "{},{},{},{},{}", uname, propId, change, current, funcName );
    }

}
