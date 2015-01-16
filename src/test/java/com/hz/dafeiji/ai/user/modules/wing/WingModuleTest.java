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
        assertEquals( ErrorCode.WING_LEVELUP_REACHED_LIMIT, errorCode );//等级已经抵达上限

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
    @SuppressWarnings("UnusedDeclaration")
    private void clearUserCash(){

    }
    @Test
    public void testSell() throws Exception{

        Wing w1 = module.add( WingTempletCfg.getWingTempletById(200401) );//增加一架僚机
        Wing w2 = module.add( WingTempletCfg.getWingTempletById(200402) );//增加一架僚机

        assertEquals( 2, module.getAllWings().size() );

        long[] sells = new long[]{w1.getId(), w2.getId()};
        int cash = module.sell( sells );
        assertEquals( 0, module.getAllWings().size() );
        assertEquals( 650, cash );

        w1 = module.add( WingTempletCfg.getWingTempletById(200401) );//增加一架僚机
        w2 = module.add( WingTempletCfg.getWingTempletById(200402) );//增加一架僚机

        w1.setLevel( 2 );//设置等级为2
        sells = new long[]{w1.getId(), w2.getId()};
        cash = module.sell( sells );
        assertEquals( 0, module.getAllWings().size() );
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

        System.out.println( module.getAllWings() );
    }

    @Test
    public void testGetAll() throws Exception{
        Map<Long, Wing> all = module.getAllWings();
        System.out.println( all );
    }

    @Test
    public void testQualityUp() throws Exception{
        Wing w1 = module.add( WingTempletCfg.getWingTempletById(200401) );//增加一架僚机
        ErrorCode errorCode = ErrorCode.SUCCESS;

        //测试不存在的僚机id
        try{
            module.QualityUp( 0, new long[0] );
        }catch( ClientException e ){
            errorCode = e.getCode();
            assertEquals( ErrorCode.WING_NOT_FOUND, errorCode );
        }

        //测试等级不够
        try{
            module.QualityUp( w1.getId(), new long[0] );
        }catch( ClientException e ){
            errorCode = e.getCode();
            assertEquals( ErrorCode.WING_UPGRADE_LEVEL_UNDER_LIMIT, errorCode );
        }


        //调整等级到可以升级品阶的最大
        w1.setLevel( 10 );
        try{
            module.QualityUp( w1.getId(), new long[0] );
        }catch( ClientException e ){
            errorCode = e.getCode();
            assertEquals( ErrorCode.STUFF_NOT_ENOUGH, errorCode );
        }

        addCash( w1.getWqTemplet().getAdvanceGold() );//给点钱
        addStuff(533001,1  );//给点道具
        int oldLevel = w1.getLevel();
        module.QualityUp( w1.getId(), new long[0] );
        assertEquals( 2, w1.getQuality() );//升阶
        assertEquals( oldLevel, w1.getLevel() );//等级不变
        assertEquals( 2700, w1.getExp() );//经验自动补齐到此品阶此等级的经验


        //继续升第三阶
        w1.setLevel( 20 );
        addCash( w1.getWqTemplet().getAdvanceGold() );//给点钱
        addStuff(533001,2  );//给点道具
        oldLevel = w1.getLevel();
        module.QualityUp( w1.getId(), new long[0] );

        assertEquals( 3, w1.getQuality() );//升阶
        assertEquals( oldLevel, w1.getLevel() );//等级不变
        assertEquals( 19000, w1.getExp() );//经验自动补齐到此品阶此等级的经验


        //继续升第四阶
        w1.setLevel( 30 );
        addCash( w1.getWqTemplet().getAdvanceGold() );//给点钱
        addStuff(533001,3  );//给点道具
        oldLevel = w1.getLevel();
        module.QualityUp( w1.getId(), new long[0] );
        assertEquals( 4, w1.getQuality() );//升阶
        assertEquals( oldLevel, w1.getLevel() );//等级不变
        assertEquals( 63800, w1.getExp() );//经验自动补齐到此品阶此等级的经验

        //继续升第五阶,超过此飞机的品质上限，抛出异常
        //w1.setLevel( 30 );
        //addCash( w1.getWqTemplet().getAdvanceGold() );//给点钱
        try {

            module.QualityUp( w1.getId(), new long[0] );
        }catch( ClientException e ){
            errorCode = e.getCode();
        }
        assertEquals( ErrorCode.WING_UPGRADE_OVER_LIMIT, errorCode );


        w1 = module.add( WingTempletCfg.getWingTempletById( 200804 ) );
        w1.setQuality( 5 );
        w1.setLevel( 45 );

        //被吞噬的僚机数量不够
        try{
            module.QualityUp( w1.getId(), new long[0] );
        }catch( ClientException e ){
            errorCode = e.getCode();
        }
        assertEquals( ErrorCode.WING_SWALLOW_COUNT_INVALID, errorCode );

        //被吞噬的僚机不存在
        try{
            module.QualityUp( w1.getId(), new long[1] );
        }catch( ClientException e ){
            errorCode = e.getCode();
        }
        assertEquals( ErrorCode.WING_NOT_FOUND, errorCode );


        try{

            module.QualityUp( w1.getId(), new long[]{w1.getId()} );
        }catch( ClientException e ){
            errorCode = e.getCode();
        }
        assertEquals( ErrorCode.WING_SWALLOW_SELF, errorCode );

        //准备一个打算被吞噬的僚机
        Wing w2 = module.add( WingTempletCfg.getWingTempletById( 200804 ) );
        w2.setQuality( 5 );
        w2.setLevel( 45 );
        w2.setCurrent( true );

        try{

            module.QualityUp( w1.getId(), new long[]{w2.getId()} );
        }catch( ClientException e ){
            errorCode = e.getCode();
        }
        assertEquals( ErrorCode.WING_SWALLOW_IS_CURRENT, errorCode );

        w2.setCurrent( false );
        addCash( w1.getWqTemplet().getAdvanceGold() );//给点钱
        oldLevel = w1.getLevel();
        int oldWingCount = module.getAllWings().size();//所有僚机的数量
        module.QualityUp( w1.getId(), new long[]{w2.getId()} );
        assertEquals( 6, w1.getQuality() );//升阶
        assertEquals( oldLevel, w1.getLevel() );//等级不变
        assertEquals( 258500, w1.getExp() );//经验自动补齐到此品阶此等级的经验
        assertEquals( oldWingCount-1, module.getAllWings().size() );//僚机的数量减去一（被吞噬）

    }

    @Test
    public void testGetAddtionCollection(){
        Wing w1 = module.add( WingTempletCfg.getWingTempletById(200401) );//增加一架僚机
        module.setCurrentWing( w1.getId() );
        System.out.println( module.getAddtionCollection() );

        w1.setQuality( 3 );
        System.out.println( module.getAddtionCollection() );
    }


    @Test
    public void testSetLock(){
        Wing w1 = module.add( WingTempletCfg.getWingTempletById(200401) );//增加一架僚机
        assertEquals( false, w1.isLock() );
        module.setLock( w1.getId(), true );
        assertEquals( true, w1.isLock() );
    }

    private void addStuff( int propId, int count ){
        user.getModuleManager().getAwardModule().addAward(
                propId + "," + count, "WingModuleTest.test" );//给点道具
    }
    @Test
    public void testCompose(){
        int templetId = 200402;//此战机通过配置表检测是不能合成的
        ErrorCode errorCode;
        try{
            module.compose( templetId );
        }catch( ClientException e ){
            errorCode = e.getCode();
            assertEquals( ErrorCode.WING_CAN_NOT_COMPOSE, errorCode );
        }

        int wingCount = module.getAllWings().size();
        addStuff( 534002, 60 );
        templetId = 200801;
        module.compose( templetId );//完全用碎片合成，不使用万能碎片

        assertEquals( ++wingCount, module.getAllWings().size() );

        //只有一半碎片
        addStuff( 534002, 30 );

        try{
            module.compose( templetId );
        }catch( ClientException e ){
            errorCode = e.getCode();
            assertEquals( ErrorCode.STUFF_NOT_ENOUGH, errorCode );
        }
        //再给一半万能碎片
        addStuff( PropIdDefine.SL_WAN_NENG_LIAO_JI, 30 );
        module.compose( templetId );//success
        assertEquals( ++wingCount,module.getAllWings().size() );
        System.out.println( user.getModuleManager().getStuffModule().getAllStuff());
    }
}