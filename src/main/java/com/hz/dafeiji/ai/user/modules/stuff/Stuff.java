package com.hz.dafeiji.ai.user.modules.stuff;

import com.hz.dafeiji.cfg.reward.StuffTemplet;
import lombok.Data;

/**
 * user         LIUKUN
 * time         2015-1-12 18:13
 * 道具材料实体类
 */
@Data
public class Stuff{
    private StuffTemplet    templet;
    private int             count;
}
