package com.hz.dafeiji.ai.user.modules.wing;

import com.bbz.tool.common.StrUtil;
import com.bbz.tool.common.Transform;
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

import java.util.Map;

/**
 * user         LIUKUN
 * time         2015-1-7 14:24
 * 僚机系统
 */

public class WingModule{

    public static final String clazName = WingModule.class.getSimpleName();

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
            throw new ClientException( ErrorCode.WING_NOT_FOUND, "wingId=" + wingId );
        }
        return wing;
    }


    /**
     * 僚机升级，输入参数请不要为空
     *
     * @param id     被升级的僚机id   不能为null
     * @param stuffs 道具模板id列表   不能为null
     * @param swallowedWings  将被吞噬的僚机唯一ID的列表数组
     * @return 升级后僚机的等级
     */
    public int levelUp( long id, int[] stuffs, long[] swallowedWings ){
        Wing wing = getWingById( id );
        Wing[] swallowWings = checkSwallowWings( swallowedWings, wing );

        int expAdd = calcExp( stuffs, swallowWings );


        int needCash = (int) (expAdd * Define.JIN_BI_WING_UP);
        String award = PropIdDefine.CASH_JIN_BI + "," + needCash;
        for( int stuff : stuffs ) {
            award += "," + stuff + ",1";
        }

        awardModule.reduceAward( award, clazName + ".leveUp()" );

        int maxLevel = WingExpCfg.getMaxLevel( wing.getQuality(), wing.getExp(), expAdd );
        wing.setLevel( maxLevel );

        int currentExp = wing.getExp() + expAdd;
        int maxLevelInQuality = wing.getWqTemplet().getMaxLv();//当前品阶的最大等级
        currentExp = Math.min( currentExp, WingExpCfg.getExp( wing.getQuality(),maxLevelInQuality) );

        wing.setExp( currentExp );

        removeWings( swallowWings );//吞噬材料僚机

        db.update( wing );
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
            exp += WingExpCfg.getExp( wing.getTemplet().getQuality(), wing.getLevel() ) * Define.LIAO_JI_TUN_SHI;
            exp += wing.getWqTemplet().getExpInitial();

        }
        for( int stuffTempletId : stuffs ) {//计算经验卡
            StuffTemplet templet = StuffTempletCfg.getStuffTempletById( stuffTempletId );
            int[] data = Transform.ArrayType.toInts( templet.getAwards() );
            int stuffId = data[0];
            if( stuffId != PropIdDefine.JING_YAN){
                throw new ClientException( ErrorCode.WING_LEVELUP_STUFF_ERROR );
            }
            exp += data[1];
        }
        return exp;
    }


    /**
     * 出售僚机
     *
     * @param idArr 要出售的僚机id数组
     * @return 获取总的金币
     */
    public int sell( long[] idArr ){
        Wing[] wings = new Wing[idArr.length];
        for( int i = 0; i < idArr.length; i++ ) {
            wings[i] = getWingById( idArr[i] );
        }
        int allCash= 0;
        for( Wing wing : wings ) {

            int cash = WingExpCfg.getExp( wing.getTemplet().getQuality(), wing.getLevel() );
            cash *= Define.JIN_BI_WING_UP;
            cash += wing.getTemplet().getCashBase();

            allCash += cash;

            awardModule.addAward( PropIdDefine.CASH_JIN_BI + "," + cash, "WingModule.sell()" );

            remove( wing );
        }
        return allCash;
    }


    /**
     * 购买僚机
     *
     * @param wingTempletId 僚机模板id
     */
    public Wing buy( int wingTempletId ){
//        WingTemplet templet = check( wingTempletId );
//        //
//        return add( templet );
        return null;
    }

    /**
     * 根据模板，创建一个僚机，并保存到内存和数据库中
     *
     * @param templet 要创建的僚机模板
     * @return 创建的僚机
     */
    public Wing add( WingTemplet templet ){
        Wing wing = new Wing( IdentityGen.INSTANCE.incrementAndGet(), templet );
        allWings.put( wing.getId(), wing );
        wing.setQuality( wing.getTemplet().getQuality() );
        db.add( wing );
        return wing;
    }
//
//    /**
//     * 通过如下条件检测玩家是否可以购买僚机
//     * 1、模板要存在
//     *
//     * @param wingTempletId 飞机模板id
//     */
//    private WingTemplet check( int wingTempletId ){
//
//        WingTemplet wt = WingTempletCfg.getWingTempletById( wingTempletId );
//        if( wt == null ) {
//            throw new ClientException( ErrorCode.WING_TEMPLET_NOT_FOUND, "planTempletId=" + wingTempletId );
//        }
//
//        return wt;
//    }

    private void setCurrentWing( Wing wing ){
        if( currentWing != null ) {
            currentWing.setCurrent( false );
            db.updateWithField( currentWing, WingDataProvider.CURRENT_FIELD, false );
        }


        wing.setCurrent( true );
        db.updateWithField( wing, WingDataProvider.CURRENT_FIELD, true );
        currentWing = wing;
    }

    /**
     * 从数据库和内存中删除僚机数组
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

    /**
     * 检测玩家传过来的被吞噬的僚机是否合法
     * 1、僚机要存在
     * 2、不能是要进阶的僚机自身
     * 3、不能是当前出战的了解
     *
     * @param ids       被吞噬僚机的id数组
     * @return          被吞噬的僚机列表
     */
    private Wing[] checkSwallowWings( long[] ids, Wing upgrageWing ){
        Wing[] wings = new Wing[ids.length];
        for( int i = 0; i < ids.length; i++ ) {
            if( upgrageWing.getId() == ids[i] ){
                throw new ClientException( ErrorCode.WING_UPGRADE_SELF );
            }

            Wing wing = getWingById( ids[i] );
            if( wing.isCurrent() ){
                throw new ClientException( ErrorCode.WING_UPGRADE_IS_CURRENT );
            }
            wings[i] = wing;

        }
        return wings;
    }

    public Map<Long, Wing> getAll(){
        return allWings;
    }

    /**
     * 品质升级
     * @param wingId                要进阶的僚机唯一id
     * @param swallowedWings        打算被吞噬的僚机id
     */
    public void QualityUp( long wingId, long[] swallowedWings ){
        Wing wing = getWingById( wingId );
        if( wing.getQuality() >= wing.getTemplet().getQualityMax() ){
            throw new ClientException( ErrorCode.WING_UPGRADE_OVER_LIMIT );
        }

        if( wing.getLevel() != wing.getWqTemplet().getMaxLv() ){
            throw new ClientException( ErrorCode.WING_UPGRADE_LEVEL_UNDER_LIMIT );
        }

        Wing[]  wings = checkSwallowWings( swallowedWings, wing );
        if( wings.length < wing.getWqTemplet().getAdvanceWing() ){
            throw new ClientException( ErrorCode.WING_UPGRADE_WING_NOT_ENOUGH );

        }

        String award = "";
        if( wing.getWqTemplet().getAdvanceGold() != 0 ){
            award = PropIdDefine.CASH_JIN_BI + "," + wing.getWqTemplet().getAdvanceGold() + ",";
        }
        if( wing.getWqTemplet().getAdvanceJewel() != 0 ){
            award = PropIdDefine.CASH_ZUAN_SHI + "," + wing.getWqTemplet().getAdvanceJewel() + ",";
        }
        if( wing.getWqTemplet().getAdvanceCard() != 0 ){
            award = PropIdDefine.ZH_JIN_JIE_KA + "," + wing.getWqTemplet().getAdvanceGold() + ",";
        }

        award = StrUtil.removeLastChar( award );

        awardModule.reduceAward( award, clazName + ".QualityUp" );
        removeWings( wings );//吞噬僚机

        wing.setQuality( wing.getQuality() + 1 );
        int currentExp = WingExpCfg.getExp( wing.getQuality(), wing.getLevel() );
        wing.setExp( currentExp );
        db.update( wing );

    }

    /**
     * 清空所有数据，用于测试，请慎重使用
     */
    void removeAll(){
        allWings.clear();
        db.removeAll();
    }

    public Wing getCurrentWing(){
        return currentWing;
    }
}
