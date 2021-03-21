package com.evan.wj.result;

public class ResultFactory {

    /**
     *
     * @param message
     * @return 返回code 400 message
     */
    public static Result buildFailResult(String message) {

        return buildResult(ResultCode.FATL,message,null);
    }

    /**
     *
     * @param data
     * @return 返回 code 200 message 注册成功
     */
    public static Result buildSuccessResult(Object data) {
        return buildResult(ResultCode.SUCCESS,"注册成功",null);
    }

    /**
     *
     * @param resultCode
     * @param message
     * @param data
     * @return 调用 枚举类 ResultCode ，返回给 buildResult: code,message
     */
    public static Result buildResult(ResultCode resultCode,String message,Object data){
        return  buildResult(resultCode.code,message,data);
    }

    /**
     *
     * @param resultCode
     * @param message
     * @param data
     * @return 调用Result 返回code ,message
     */
    public static Result buildResult(int resultCode,String message,Object data){
        return  new Result(resultCode,message,data);
    }
}
