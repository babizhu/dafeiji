package com.hz.dafeiji.ai.user.modules.wing;

import com.bbz.tool.identity.IdentityGen;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.modules.ModuleManager;
import com.hz.dafeiji.ai.user.modules.award.AwardModule;
import com.hz.dafeiji.cfg.define.Define;
import com.hz.dafeiji.cfg.define.PropIdDefine;
import com.hz.dafeiji.cfg.manual.WingExpCfg;
import com.hz.dafeiji.cfg.reward.StuffTemplet;
import com.hz.dafeiji.cfg.reward.StuffTempletCfg;
import com.hz.dafeiji.cfg.wing.WingTemplet;
import com.hz.dafeiji.cfg.wing.WingTempletCfg;

import java.util.Map;

/**
 * user         LIUKUN
 * time         2015-1-7 14:24
 * 僚机系统
 */

public class WingModule{

    private final WingDataProvider db;
    private final AwardModule awardModule;

    /**
     * 所有的僚机
     */
    private Map<Long, Wing> allWings;

    /**
     * 当前出战的僚机
     */
    private Wing currentWing;


    public WingModule( ModuleManager moduleManager ){
        db = new WingDataProvider( moduleManager.getUserName() );
        awardModule = moduleManager.getAwardModule();

        allWings = db.getMapAll();

        findCurrentWing();
    }

    /**
     * 查找当前出战的僚机，设置到成员变量上
     */
    private void findCurrentWing(){
        for( Wing wing : allWings.values() ) {

            if( wing.isCurrent() ) {
                currentWing = wing;
            }
        }

    }

    /**
     * 设置出战僚机
     *
     * @param wingId 要出战僚机的唯一ID
     */
    public void setCurrentWing( long wingId ){
        Wing newCurrentWing = getWingById( wingId );//如果没有僚机存在，会报异常
        setCurrentWing( newCurrentWing );
    }

    /**
     * 根据唯一id查找僚机，如果不存在会抛出异常
     *
     * @param wingId 僚机的唯一id
     * @return plane
     */
    private Wing getWingById( long wingId ){
        Wing wing = allWings.get( wingId );
        if( wing == null ) {
            throw new ClientException( ErrorCode.WING_NOT_FOUND );
        }
        return wing;
    }


    /**
     * 僚机升级
     *
     * @param id     被升级的僚机id
     * @param stuffs 道具模板id列表
     * @param wings  将被吞噬的僚机唯一ID列表
     * @return 升级后僚机的等级
     */
    public int levelUp( long id, int[] stuffs, long[] wings ){
        Wing wing = getWingById( id );
        Wing[] wingArr = getWingArrById( wings );

        int expAdd = calcExp( stuffs, wingArr );

        int needCash = (int) (expAdd * Define.JIN_BI_WING_UP);//TODO 黄同学来确定
        awardModule.reduceAward( PropIdDefine.CASH_JIN_BI + "," + needCash, "WingModule.leveUp()" );

        int maxLevel = WingExpCfg.getMaxLevel( wing.getTemplet().getQuality(), wing.getExp(), expAdd );
        wing.setLevel( maxLevel );

        removeWings( wingArr );
        //TODO 扣除道具

        db.updateWithField( wing, WingDataProvider.LEVEL_FIELD, maxLevel );
        return maxLevel;
    }


    /**
     * 计算玩家上传的经验卡以及僚机所产生的经验
     *
     * @param stuffs  经验卡的模板id
     * @param wingArr 将被吞噬的僚机数组
     * @return 返回stuffs以及wings所产生的经验
     */
    private int calcExp( int[] stuffs, Wing[] wingArr ){
        int exp = 0;
        for( Wing wing : wingArr ) {
            exp += WingExpCfg.getExp( wing.getTemplet().getQuality(), wing.getLevel() ) * Define.ZHUANG_BEI_CHAI_FEN;//TODO 黄同学来确定
        }
        for( int stuffTempletId : stuffs ) {//计算经验卡
            StuffTemplet templet = StuffTempletCfg.getStuffTempletById( stuffTempletId );
            exp += Integer.parseInt( templet.getAwards() );
        }
        return exp;
    }


    /**
     * 出售僚机
     *
     * @param id 要出售的僚机id
     * @return 获取的金币
     */
    public int sell( long id ){
        Wing wing = getWingById( id );
        int cash = 0;//TODO 计算僚机兑换的金币

        awardModule.addAward( PropIdDefine.CASH_JIN_BI + "," + cash, "WingModule.sell()" );
        remove( wing );
        return cash;
    }


    /**
     * 购买僚机
     *
     * @param wingTempletId
     */
    public Wing buy( int wingTempletId ){
        WingTemplet templet = check( wingTempletId );
        //TODO
        return add( templet );
    }

    /**
     * 根据模板，创建一个僚机，并保存到内存和数据库中
     *
     * @param templet 要创建的僚机模板
     * @return 创建的僚机
     */
    private Wing add( WingTemplet templet ){
        Wing wing = new Wing( IdentityGen.INSTANCE.incrementAndGet(), templet );
        allWings.put( wing.getId(), wing );
        db.add( wing );
        return wing;
    }

    /**
     * 通过如下条件检测玩家是否可以购买僚机
     * 1、模板要存在
     *
     * @param wingTempletId 飞机模板id
     */
    private WingTemplet check( int wingTempletId ){

        WingTemplet wt = WingTempletCfg.getWingTempletById( wingTempletId );
        if( wt == null ) {
            throw new ClientException( ErrorCode.WING_TEMPLET_NOT_FOUND, "planTempletId=" + wingTempletId );
        }

        return wt;
    }

    private void setCurrentWing( Wing wing ){
        if( currentWing != null ) {
            currentWing.setCurrent( false );
            db.updateWithField( currentWing, WingDataProvider.CURRENT_FIELD, false );
        }

        currentWing.setCurrent( true );
        db.updateWithField( wing, WingDataProvider.CURRENT_FIELD, true );
        currentWing = wing;
    }

    /**
     * 从数据库和内存中删除僚机
     */
    private void removeWings( Wing[] removeWings ){
        for( Wing wing : removeWings ) {
            remove( wing );
        }
    }

    private void remove( Wing wing ){
        db.remove( wing );
        allWings.remove( wing.getId() );
    }

    private Wing[] getWingArrById( long[] ids ){
        Wing[] wings = new Wing[ids.length];
        for( int i = 0; i < ids.length; i++ ) {
            wings[i] = getWingById( ids[i] );
        }
        return wings;
    }

    public Map<Long, Wing> getAll(){
        return allWings;
    }
}
