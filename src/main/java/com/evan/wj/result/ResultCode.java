package com.evan.wj.result;

public enum  ResultCode {

    SUCCESS(200),
    FATL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;
    ResultCode (int code){
        this.code = code;
    }

}
