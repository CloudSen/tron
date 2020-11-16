package com.clouds3n.common.torn.autoconfigure.exception;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author clouds3n
 * @time 2020-11-14 09:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {
    private Integer code;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Integer code) {
        this.code = code;
    }

    public BusinessException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public BusinessException(Throwable cause, Integer code) {
        super(cause);
        this.code = code;
    }

    public BusinessException(String msg, Throwable cause, Integer code) {
        super(msg, cause);
        this.code = code;
    }

    public BusinessException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code) {
        super(msg, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
