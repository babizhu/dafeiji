package com.hz.dafeiji.ai.user.modules.property;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * user         LIUKUN
 * time         2014-4-8 16:28
 * 管理玩家的属性包括
 * 铜钱
 * 元宝
 * 体力
 */

public class PropertyModule{

    private static final Logger logger = LoggerFactory.getLogger( PropertyModule.class );

    private final PropertyDataProvider db;
    private final UserProperty property;

    public PropertyModule( String uname ){
        //String uname1 = uname;
        db = new PropertyDataProvider( uname );
        property = db.findOne();
        //this.moduleManager = moduleManager;
    }


//    public boolean isEnough()

    /**
     * 玩家涉及到属性的更改操作
     *
     * @return >=0	:返回此属性的当前值
     * -1	:扣除失败，余值不足
     */
//    public int changeValue( UserPropertyType type, int change ){
//        int result = 0;
//        switch( type ) {
//            case CASH:
//                //result = property.addCash( change );
//                break;
//            case GOLD:
//                //result = property.addGold( change );
//                break;
//            default:
//                throw new IllegalArgumentException( type + "属性不存在相应函数" );
//        }
//        if( result == -1 ) {
//            logger.debug( uname + "奖励类型：" + type + "欲改变数值：" + change + "，出现负数，调用函数为" + funcName );
//        }
//        buildLog( type, change, result, funcName );
//        db.replace( property );
//        return result;
//    }

//    /**
//     * 构造关键数据的日志文件
//     *
//     * @param at       奖励类型
//     * @param change   改动的数值
//     * @param current  改动后的当前数值
//     * @param funcName 改动函数
//     */
//    private void buildLog( UserPropertyType at, int change, int current, String funcName ){
//        //{}依次为 用户名，物品类型，变化值，当前值，调用的方法名
//        logger.info( "{},{},{},{},{}", uname, at, change, current, funcName );
//    }


    ////////////////////////////////////以下为委托方法///////////////////////////////////
    public int getStrength(){
        return property.getStrength().getStrength();
    }

    public int getLastCalcStrengthSecond(){
        return property.getStrength().getLastCalcStrengthSecond();
    }


    public int getScore(){
        return property.getScore();
    }

    public int getScoreWeek(){
        return property.getScoreWeek();
    }

    public int getPower(){
        return property.getPower();
    }

    public int getDiamond(){
        return property.getDiamond();
    }

    public int getExp(){
        return property.getExp();
    }

    public int getCash(){
        return property.getCash();
    }

    public void setScore( int score ){
        property.setScore( score );
    }

    public void setScoreWeek( int score ){
        property.setScoreWeek( score );
    }


    public int getStengthMax(){
        return property.getStrength().getStrengthMax();
    }

    public int getEnergy(){
        return property.getEnergy();
    }

    /**
     * 如果是扣除，changeValue请用负数，同时无需考虑是否够扣的问题，AwardModule会统一处理
     *
     * @param changeValue 变化值
     * @return 变化之后的钻石数量
     */
    public int changeDiamond( int changeValue ){
        int count = property.getDiamond() + changeValue;
        property.setDiamond( count );
        db.updateWithField( PropertyDataProvider.DIAMOND_FIELD, count );
        return count;
    }

    /**
     * 如果是扣除，changeValue请用负数，同时无需考虑是否够扣的问题，AwardModule会统一处理
     *
     * @param changeValue 变化值
     * @return 变化之后的金币数量
     */
    public int changeCash( int changeValue ){
        int count = property.getCash() + changeValue;
        property.setCash( count );
        db.updateWithField( PropertyDataProvider.CASH_FIELD, count );
        return count;
    }

    /**
     * 修改玩家体力，如果是扣除，changeValue请用负数，同时无需考虑是否够扣的问题，AwardModule会统一处理
     *
     * @param changeValue 变化值
     * @return 变化之后的体力数量
     */
    public int changeStrength( int changeValue ){

        int st = property.getStrength().changeStrength( changeValue );
        db.replace( property );//体力比较特殊，这里简化处理先
        return st;
    }

    /**
     * 修改玩家能源，如果是扣除，changeValue请用负数，同时无需考虑是否够扣的问题，AwardModule会统一处理
     * @param changeValue 能源变化值
     * @return 变化后的能源数量
     */
    public int changeEnergy(int changeValue){
        int energy = property.getEnergy() + changeValue;
        property.setEnergy(energy);
        db.updateWithField(PropertyDataProvider.ENERGY_FIELD, energy);
        return energy;
    }

    @Override
    public String toString(){
        return "PropertyModule{" +
                "property=" + property +
                '}';
    }

    //////////////////////////////////////////测试用//////////////////////////////////////////////

    /**
     * 删除玩家的数据库记录，仅仅为测试用例使用
     */
    public void removeUserData(){
        db.remove();
    }


}
