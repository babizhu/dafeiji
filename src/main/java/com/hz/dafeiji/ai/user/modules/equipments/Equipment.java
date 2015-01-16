package com.hz.dafeiji.ai.user.modules.equipments;


import com.bbz.tool.db.IdentityObj;
import com.bbz.tool.identity.IdentityGen;
import com.hz.dafeiji.cfg.equipment.EquipmentTemplet;
import com.hz.dafeiji.cfg.equipment.EquipmentTempletCfg;
import lombok.Data;

/**
 * user         LIUKUN
 * time         14-3-25 下午1:43
 */

@Data
public class Equipment implements IdentityObj{

    private final EquipmentTemplet templet;

    /**
     * 装备ID
     */
    private long id;

    /**
     * 装备等级
     */
    private int level;

    /**
     * 装备品质
     */
    private int quality;

    /**
     * 是否装备上
     */
    private int loaded;

    /**
     * 是否是已删除装备
     */
    private int isDelete;

    /**
     * 是否已被锁定
     */
    private int locked;


    /**
     * 从数据库取出数据构造对象
     * @param id  数据库ID
     * @param templetId  模版ID
     * @param level  等级
     * @param quality  品质
     * @param loaded  是否装备上
     * @param isDelete 是否已删除
     */
    public Equipment(long id, int templetId, int level, int quality, int loaded, int isDelete, int locked){
        this.id = id;
        this.templet = EquipmentTempletCfg.getEquipmentTempletById( templetId );
        this.level = level;
        this.quality = quality;
        this.loaded = loaded;
        this.isDelete = isDelete;
        this.locked = locked;
    }

    /**
     * 从配置表构造新装备
     * @param templetId  模版ID
     */
    public Equipment(int templetId){
        this.id = IdentityGen.INSTANCE.incrementAndGet();
        this.templet = EquipmentTempletCfg.getEquipmentTempletById( templetId );
        this.level = 1;
        this.quality = templet.getQuality();
        this.loaded = 0;
        this.isDelete = 0;
        this.locked = 0;
    }

    /**
     * 装备升级
     */
    public void levelUp(){
        this.level++;
    }

    /**
     * 装备进阶
     */
    public void upgradeQuality(){
        this.quality++;
    }

    /**
     * 锁定装备
     */
    public void lock(){
        this.locked = 1;
    }

    /**
     * 解锁装备
     */
    public void unlock(){
        this.locked = 0;
    }

    /**
     * 判断是否已经被删除
     * @return boolean
     */
    public boolean isRemoved(){ return this.isDelete > 0; }

    /**
     * 判断是否是上阵的装备
     * @return boolean
     */
    public boolean isLoaded(){
        return this.loaded > 0;
    }

    /**
     * 判断是否北锁定
     * @return boolean
     */
    public boolean isLocked(){ return this.locked > 0; }
}
