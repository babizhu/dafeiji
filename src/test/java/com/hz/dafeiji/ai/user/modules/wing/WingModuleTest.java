package com.hz.dafeiji.ai.user.modules.wing;

import com.bbz.tool.common.Transform;
import com.hz.dafeiji.ai.ClientException;
import com.hz.dafeiji.ai.ErrorCode;
import com.hz.dafeiji.ai.user.User;
import com.hz.dafeiji.cfg.define.PropIdDefine;
import com.hz.dafeiji.cfg.reward.StuffTempletCfg;
import com.hz.dafeiji.cfg.wing.WingExpTempletCfg;
import com.hz.dafeiji.cfg.wing.WingQurlityTempletCfg;
import com.hz.dafeiji.cfg.wing.WingTemplet;
import com.hz.dafeiji.cfg.wing.WingTempletCfg;
import com.hz.util.D;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class WingModuleTest{

    String uname = D.TEST_USER_NAME;
    User user = new User( uname );
    private final WingModule module = user.getModuleManager().getWingModule();

    /**
     * 初始化配置表
     */
    @BeforeClass
    public static void init(){
        WingTempletCfg.init();
        WingExpTempletCfg.init();
        WingQurlityTempletCfg.init();
        StuffTempletCfg.init();
        //PlaneQurlityTempletCfg.init();

    }

    @Before
    public void setUp() throws Exception{
        module.removeAll();
    }

    @After
    public void tearDown() throws Exception{


    }

    @Test
    public void testSetCurrentWing() throws Exception{

        int templetId = 200401;
        WingTemplet wt = WingTempletCfg.getWingTempletById( templetId );
        Wing w1 = module.add( wt );

        templetId = 200402;
        wt = WingTempletCfg.getWingTempletById( templetId );
        Wing wing = module.add( wt );
        module.setCurrentWing( wing.getId() );
        assertEquals( module.getCurrentWing(), wing );

        module.setCurrentWing( w1.getId() );
        assertEquals( module.getCurrentWing(), w1 );

        module.add( wt );
        assertEquals( module.getCurrentWing(), w1 );
    }

    @Test
    public void testLevelUp() throws Exception{
        long[] swallowWings = new long[0];
        int[] stuffs = new int[0];


        int templetId = 200401;
        WingTemplet wt = WingTempletCfg.getWingTempletById( templetId );
        Wing w1 = module.add( wt );

        //测试长度为0的道具表和长度为0的吞噬僚机表 不会抛异常
        module.levelUp( w1.getId(), stuffs, swallowWings );

        //533005	2级经验卡	1000	僚机吞噬会获得1000经验
        stuffs = new int[1];
        stuffs[0] = 533005;

        int data[] = Transform.ArrayType.toInts( StuffTempletCfg.getStuffTempletById( 533005 ).getAwards() );
        int exp = data[1];//这个经验卡获得的经验值

        ErrorCode errorCode = ErrorCode.SUCCESS;
        try {
            module.levelUp( w1.getId(), stuffs, swallowWings );
        } catch( ClientException e ) {
            errorCode = e.getCode();
        }
        assertEquals( ErrorCode.USER_CASH_NOT_ENOUGH, errorCode );//金钱不足

        addCash( exp / 10 );//给点钱
        module.levelUp( w1.getId(), stuffs, swallowWings );
        assertEquals( 7, w1.getLevel() );
        assertEquals( exp, w1.getExp() );//升级成功

        addCash( exp / 10 );//给点钱
        module.levelUp( w1.getId(), stuffs, swallowWings );
        assertEquals( 10, w1.getLevel() );//1品阶最高等级为10
        assertEquals( 1800, w1.getExp() );//经验应该为1800，尽管实际给予了2000经验

        //在已经升级到1品阶满级（10级）的情况下，继续升级
        addCash( exp / 10 );
        try {
            module.levelUp( w1.getId(), stuffs, swallowWings );
        } catch( ClientException e ) {
            errorCode = e.getCode();
        }
        assertEquals( ErrorCode.WING_LEVEL_REACHED_LIMIT, errorCode );//等级已经抵达上限

        user.getModuleManager().getAwardModule().reduceAward(
                PropIdDefine.CASH_JIN_BI + "," + exp / 10, "WingModuleTest.testLevelUp" );//钱归0

    }

    /**
     * 配合僚机升级需要给玩家增加一点钱
     * @param cash  要增加的钱
     */
    private void addCash( int cash ){
        user.getModuleManager().getAwardModule().addAward(
                PropIdDefine.CASH_JIN_BI + "," + cash, "WingModuleTest.testLevelUp" );//给点钱
    }

    /**
     * 清空玩家的钱
     */
    private void clearUserCash(){

    }
    @Test
    public void testSell() throws Exception{

        Wing w1 = module.add( WingTempletCfg.getWingTempletById(200401) );//增加一架僚机
        Wing w2 = module.add( WingTempletCfg.getWingTempletById(200402) );//增加一架僚机

        assertEquals( 2, module.getAll().size() );

        long[] sells = new long[]{w1.getId(), w2.getId()};
        int cash = module.sell( sells );
        assertEquals( 0, module.getAll().size() );
        assertEquals( 650, cash );

        w1 = module.add( WingTempletCfg.getWingTempletById(200401) );//增加一架僚机
        w2 = module.add( WingTempletCfg.getWingTempletById(200402) );//增加一架僚机

        w1.setLevel( 2 );//设置等级为2
        sells = new long[]{w1.getId(), w2.getId()};
        cash = module.sell( sells );
        assertEquals( 0, module.getAll().size() );
        assertEquals( 660, cash );




    }

    @Test
    public void testBuy() throws Exception{

    }

    @Test
    public void testAdd() throws Exception{
        int templetId = 200401;
        WingTemplet wt = WingTempletCfg.getWingTempletById( templetId );
        module.add( wt );

        templetId = 200402;
        wt = WingTempletCfg.getWingTempletById( templetId );
        module.add( wt );

        System.out.println( module.getAll() );
    }

    @Test
    public void testGetAll() throws Exception{
        Map<Long, Wing> all = module.getAll();
        System.out.println( all );
    }

    @Test
    public void testQualityUp() throws Exception{
        Wing w1 = module.add( WingTempletCfg.getWingTempletById(200401) );//增加一架僚机
        ErrorCode errorCode = ErrorCode.SUCCESS;
        try{
            module.QualityUp( w1.getId(), new long[0] );
        }catch( ClientException e ){
            errorCode = e.getCode();
        }
        assertEquals( ErrorCode.WING_UPGRADE_LEVEL_UNDER_LIMIT, errorCode );


        //调整等级到可以升级品阶的最大
        w1.setLevel( 10 );
        try{
            module.QualityUp( w1.getId(), new long[0] );
        }catch( ClientException e ){
            errorCode = e.getCode();
        }
        assertEquals( ErrorCode.WING_UPGRADE_LEVEL_UNDER_LIMIT, errorCode );


    }
}