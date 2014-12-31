package com.hz.dafeiji.ai.user.modules.fighters;


import com.bbz.tool.common.MiscUtil;
import com.bbz.tool.db.IdentityObj;
import com.google.common.collect.Sets;
import com.hz.dafeiji.ai.user.modules.equipments.Equipment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

/**
 * user         LIUKUN
 * time         14-3-25 下午1:36
 * 玩家手中的英雄
 */


@ToString(callSuper = false, includeFieldNames = true)
@EqualsAndHashCode(callSuper = true)
@Data()
public class Hero extends BaseFighter implements IdentityObj{

    /**
     * 唯一标识
     */
    private final long id;

    /**
     * 名字
     */
    private String name;
    /**
     * 装备
     */
    private Set<Equipment> equipments = Sets.newHashSet();

    /**
     * 所在位置
     * -1表示不在战斗位置上，处于闲置状态
     */
    private int position;

    /**
     * 经验
     */
    private int exp;

    public Hero( long id ){
        //super( templet );
        this.id = id;
    }

    public static void main( String[] args ){

    }

    /**
     * 升级
     */
    public void levelUp(){

    }

    /**
     * 通过经验计算等级,等级从1开始，即0经验等于1级
     *
     * @return 等级
     */
    public int getLevel(){
        int[] data = new int[]{0, 100, 500, 200, 10000, 50000};//暂时手写，应该从配置表中获取
        return MiscUtil.calcLevel( data, exp, 1 );
    }
}
