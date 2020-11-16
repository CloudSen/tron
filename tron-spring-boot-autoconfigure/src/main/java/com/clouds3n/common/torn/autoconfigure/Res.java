package com.clouds3n.common.torn.autoconfigure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 全局API统一返回类
 *
 * @author CloudS3n
 * @date 2019/11/29 16:05
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("WeakerAccess")
public class Res implements Serializable {

    private static final long serialVersionUID = 8691869425240315539L;
    private Boolean success;
    private Integer code;
    private String msg;
    private Object data;

    public static Res ok() {
        return new Res(
            Boolean.TRUE,
            HttpStatus.OK.value(),
            AutoConfigConstants.SUCCESS_OPERATE,
            null
        );
    }

    public static Res ok(Object data) {
        return ok().setData(data);
    }

    public static Res error() {
        return new Res(
            Boolean.FALSE,
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            AutoConfigConstants.ERROR_OPERATE,
            null
        );
    }

    public static Res error(String reason) {
        return error().setMsg(reason);
    }

    public static Res error(String reason, Object data) {
        return error().setMsg(reason).setData(data);
    }

    public static Res error(Integer code, String reason, Object data) {
        return error().setCode(code).setMsg(reason).setData(data);
    }

    public static Res error401() {
        return new Res()
            .setSuccess(Boolean.FALSE)
            .setCode(HttpStatus.UNAUTHORIZED.value())
            .setMsg(AutoConfigConstants.NO_AUTHORIZATION);
    }

    public static Res error401(String msg) {
        return error401().setMsg(msg);
    }
}
