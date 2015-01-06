package com.hz.dafeiji.ai.user.modules.plane;


import com.bbz.tool.identity.IdentityGen;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.ModuleManager;
import com.hz.dafeiji.cfg.plane.PlaneTemplet;
import com.hz.dafeiji.cfg.plane.PlaneTempletCfg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    /**
     * 所有的飞机
     */
    private Map<Long, Plane> planes;

    /**
     * 当前出战的飞机
     */
    private Plane currentPlane;
    //private CurrentPlaneData currentPlaneData;

    private final ModuleManager moduleManager;

    public PlaneModule( String uname, ModuleManager moduleManager ){
        this.moduleManager = moduleManager;
        db = new PlaneDataProvider( uname );
        //db1 = new CurrentPlaneDataProvider( uname );

        planes = db.getMapAll();
        //currentPlaneData = db1.findOne();

        findCurrentPlane();
    }

    public Map<Long, Plane> getAll(){
        return planes;
    }

    /**
     * 根据唯一id查找飞机，如果不存在会自动抛出异常
     *
     * @param planeId 飞机的唯一id
     * @return plane
     */
    private Plane getPlaneById( long planeId ){
        Plane plane = planes.get( planeId );
        if( plane == null ) {
            throw new ClientException( ErrorCode.PLANE_NOT_FOUND );
        }
        return plane;
    }

    void levelUp( long planeId ){
        Plane plane = getPlaneById( planeId );
        plane.setLevel( plane.getLevel() + 1 );
    }

    /**
     * 检测相同模板的飞机是否已经存在
     *
     * @param planeTempletId 需要检测的模板id
     * @return true：存在
     * false：不存在
     */
    private boolean isDuplicate( int planeTempletId ){
        for( Plane plane : planes.values() ) {
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
    private Plane doCreate( PlaneTemplet templet ){
        long id = IdentityGen.INSTANCE.incrementAndGet();
        Plane plane = new Plane( id, templet );
        planes.put( id, plane );
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
        return doCreate( pt );
    }

    /**
     * 玩家购买飞机
     *
     * @param planeTempletId 要购买的飞机的模板id
     * @return 购买的飞机
     */
    Plane buy( int planeTempletId ){
        PlaneTemplet pt = check( planeTempletId );
        moduleManager.getAwardModule().reduceAward( pt.getPrice(), "PlaneModule.buy" );
        return doCreate( pt );
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
    void remove(){
        planes.clear();
        db.removeAll();
    }

    /**
     * 查找当前出战的飞机，设置到成员变量上
     */
    private void findCurrentPlane(){
        for( Map.Entry<Long, Plane> planeEntry : planes.entrySet() ) {
            Plane plane = planeEntry.getValue();
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
     * @param newCurrentPlane 要出战飞机
     */
    public void setCurrentPlane( Plane newCurrentPlane ){
        if( currentPlane != null ) {
            currentPlane.setCurrent( false );
            db.updateCurrentPlaneField( currentPlane );
        }

        currentPlane = newCurrentPlane;
        currentPlane.setCurrent( true );
        db.updateCurrentPlaneField( currentPlane );
    }

    public Plane getCurrentPlane(){
        return currentPlane;
    }


}
