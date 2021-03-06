package com.hz.dafeiji.ai.user.modules.plane;


import com.bbz.tool.identity.IdentityGen;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.addtion.AddtionCollection;
import com.hz.dafeiji.ai.addtion.AddtionType;
import com.hz.dafeiji.ai.addtion.AddtionValue;
import com.hz.dafeiji.ai.user.modules.ModuleManager;
import com.hz.dafeiji.ai.user.modules.award.AwardModule;
import com.hz.dafeiji.ai.user.modules.equipments.EquipmentModule;
import com.hz.dafeiji.ai.user.modules.wing.WingModule;
import com.hz.dafeiji.cfg.plane.PlaneQurlityTemplet;
import com.hz.dafeiji.cfg.plane.PlaneQurlityTempletCfg;
import com.hz.dafeiji.cfg.plane.PlaneTemplet;
import com.hz.dafeiji.cfg.plane.PlaneTempletCfg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.Map;

/**
 * user         LIUKUN
 * time         2014-12-25 16:56
 * <p/>
 * 机甲模块，也就是飞机模块，也就是传统的英雄模块
 */

public class PlaneModule{
    private static final Logger logger = LoggerFactory.getLogger( PlaneModule.class );
    private final PlaneDataProvider db;
    private final EquipmentModule equipmentModule;


    /**
     * 所有的飞机
     */
    private Map<Long, Plane> allPlanes;

    /**
     * 当前出战的飞机
     */
    private Plane currentPlane;
    //private CurrentPlaneData currentPlaneData;

//    private final ModuleManager moduleManager;
    private final AwardModule awardModule;
    private WingModule wingModule;

    public PlaneModule( String uname, ModuleManager moduleManager ){
//        this.moduleManager = moduleManager;
        awardModule = moduleManager.getAwardModule();
        equipmentModule = moduleManager.getEquipmentModule();
        wingModule = moduleManager.getWingModule();

        db = new PlaneDataProvider( uname );
        allPlanes = db.getMapAll();

       findCurrentPlane();


    }

    public Map<Long, Plane> getAll(){
        return allPlanes;
    }

    /**
     * 根据唯一id查找飞机，如果不存在会抛出异常
     *
     * @param planeId 飞机的唯一id
     * @return plane
     */
    private Plane getPlaneById( long planeId ){
        Plane plane = allPlanes.get( planeId );
        if( plane == null ) {
            throw new ClientException( ErrorCode.PLANE_NOT_FOUND );
        }
        return plane;
    }

    /**
     * 飞机升级
     * 消耗公式=round(基础消耗+（当前等级-1）*品阶消耗系数*int（（当前等级-1）/10+1）,-2)
     *
     * @param planeId 飞机id
     */
    public void levelUp( long planeId ){
        Plane plane = getPlaneById( planeId );
        int qurlityId = plane.getTemplet().getQuality();
        PlaneQurlityTemplet pqt = PlaneQurlityTempletCfg.getPlaneQurlityTempletById( qurlityId );
        if( plane.getLevel() >= pqt.getMaxLv() ) {
            throw new ClientException( ErrorCode.PLANE_REACH_MAX_LEVEL );
        }

        int needCash = calcLevelUpNeedCash( plane, pqt );
        awardModule.reduceAward( "500001," + needCash, "Plane.levelUp" );
        plane.setLevel( plane.getLevel() + 1 );
    }

    int calcLevelUpNeedCash( Plane plane, PlaneQurlityTemplet pqt ){
        int needCash = (plane.getLevel() - 1) / 10 + 1;

        needCash *= pqt.getConsumeFactor();
        needCash *= plane.getLevel() - 1;
        needCash += pqt.getConsumeBasis();

        needCash = Math.round( needCash / 100F ) * 100;

        return needCash;
    }

    /**
     * 检测相同模板的飞机是否已经存在
     *
     * @param planeTempletId 需要检测的模板id
     * @return true：存在
     * false：不存在
     */
    private boolean isDuplicate( int planeTempletId ){
        for( Plane plane : allPlanes.values() ) {
            if( plane.getTemplet().getId() == planeTempletId ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据指定的planeTempletId为玩家创建一架飞机
     * 这里并没有检测是否存在相同模板的飞机，因此不允许外部直接调用
     *
     * @param templet 指定的飞机模板
     */
    private Plane add( PlaneTemplet templet ){
        long id = IdentityGen.INSTANCE.incrementAndGet();
        Plane plane = new Plane( id, templet );
        allPlanes.put( id, plane );
        db.add( plane );
        return plane;

    }

    /**
     * 通过奖励的方式获取一架飞机
     *
     * @param planeTempletId 要奖励的飞机的模板id
     */
    public Plane create( int planeTempletId ){
        PlaneTemplet pt = check( planeTempletId );
        return add( pt );
    }

    /**
     * 玩家购买飞机
     *
     * @param planeTempletId 要购买的飞机的模板id
     * @return 购买的飞机
     */
    public Plane buy( int planeTempletId ){
        PlaneTemplet pt = check( planeTempletId );
        awardModule.reduceAward( pt.getPrice(), "PlaneModule.buy" );
        return add( pt );
        //AWARD_LOG.info(  );
    }


    /**
     * 通过如下条件检测玩家是否可以拥有此飞机
     * 1、模板要存在
     * 2、模板不能重复
     *
     * @param planeTempletId 飞机模板id
     */
    private PlaneTemplet check( int planeTempletId ){
        if( isDuplicate( planeTempletId ) ) {
            throw new ClientException( ErrorCode.PLANE_TEMPLET_DUPLICATE );
        }

        PlaneTemplet pt = PlaneTempletCfg.getPlaneTempletById( planeTempletId );
        if( pt == null ) {
            throw new ClientException( ErrorCode.PLANE_TEMPLET_NOT_FOUND, "planTempletId=" + planeTempletId );
        }
        return pt;
    }

    /**
     * 测试用
     */
    public void removeAll(){
        db.removeAll();
        allPlanes.clear();
    }

    /**
     * 查找当前出战的飞机，设置到成员变量上
     */
    private void findCurrentPlane(){
        for( Plane plane : allPlanes.values() ) {
            if( plane.isCurrent() ) {
                currentPlane = plane;
            }
        }

    }


    /**
     * 设置出战飞机
     *
     * @param planeId 要出战飞机的id
     */
    public void setCurrentPlane( long planeId ){
        Plane newCurrentPlane = getPlaneById( planeId );//如果没有飞机存在，会报异常
        setCurrentPlane( newCurrentPlane );

    }

    /**
     * 设置出战飞机
     *
     * @param plane 要出战飞机
     */
    public void setCurrentPlane( Plane plane ){
        if( currentPlane != null ) {
            currentPlane.setCurrent( false );
            db.updateCurrentPlaneField( currentPlane );
        }

        plane.setCurrent( true );
        db.updateCurrentPlaneField( plane );
        currentPlane = plane;
    }

    public Plane getCurrentPlane(){
        return currentPlane;
    }

    /**
     *  计算当前飞机在考虑各种加成之后的实际属性     *
     */
    private void calcAllAddtion( ){
        AddtionCollection allAddtion = new AddtionCollection();
        allAddtion.add( equipmentModule.getAddtionCollection() );
        allAddtion.add( wingModule.getAddtionCollection() );

        calcAddtion( allAddtion.getAddtions() );
    }

    /**
     * 计算当前飞机在考虑加成之后的实际属性
     * @param addtionMap    加成情况
     */
    private void calcAddtion( EnumMap<AddtionType, AddtionValue> addtionMap ){
        currentPlane.calcAddtion( addtionMap );
    }
}
