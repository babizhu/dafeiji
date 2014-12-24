package com.hz.dafeiji.ai.user.modules.player;

import com.hz.dafeiji.ai.user.UserStatus;
import lombok.Data;

/**
 * user         LIUKUN
 * time         2014-12-19 15:10
 * 如果数据库没有对应的值，那么查询的结果就是缺省值
 * 数字一般为0，字符串为null！！！！！
 */
@Data
public class UserBaseInfo{
    private String userName;
    private long uid;
    private String nickName;
    private UserStatus userStatus;
    private String pass;
    /**
     * 是否成年
     */
    private boolean isAdult;
}
