package com.hz.dafeiji.ai;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user         LIUKUN
 * time         14-3-26 下午7:51
 * <p/>
 * 需要通知到到客户端的异常
 */

@EqualsAndHashCode(callSuper = true)

@Data
public class ClientException extends RuntimeException{

    private final ErrorCode code;
    private String desc;

    public ClientException( ErrorCode code, String desc ){
        this.code = code;
        this.desc = desc;
    }

    public ClientException( ErrorCode code ){
        this.code = code;

    }

}
