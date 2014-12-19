package com.hz.dafeiji.ai.user.modules.player;

import com.hz.dafeiji.ai.user.UserStatus;
import lombok.Data;

/**
 * user         LIUKUN
 * time         2014-12-19 15:10
 */
@Data
public class UserBaseInfo{
    private String                  userName;
    private int                     uid;
    private String                  nickName;
    private UserStatus              userStatus;
    private String                  pass;
}
