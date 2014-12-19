package com.hz.dafeiji.net.protocol;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user         LIUKUN
 * time         2014-12-19 10:48
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class LoginRequest extends AbstractRequest{
    private String uName;
    private String uPass;
    private int sId;
}
