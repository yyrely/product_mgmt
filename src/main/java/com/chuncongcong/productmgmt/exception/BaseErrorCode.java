package com.chuncongcong.productmgmt.exception;

import lombok.Data;

/**
 * @author Hu
 * @date 2018/12/23 16:21
 */

@Data
public class BaseErrorCode implements ErrorCode{

    private int code;
    private int httpCode;
    private String message;

    public BaseErrorCode(int code, int httpCode, String message) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

    /**系统异常*/
    public static final ErrorCode SYSTEM_ERROR = new BaseErrorCode(500, 500, "system error");

    /**接口为找到*/
    public static final ErrorCode API_NOT_FOUND = new BaseErrorCode(404, 404, "api not found");

    /**接口为找到*/
    public static final ErrorCode NOT_LOGIN = new BaseErrorCode(401, 401, "not login");

}
