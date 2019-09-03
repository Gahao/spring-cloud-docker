package com.musketeers.common.utils;

/**
 * 接口返回数据的封装类
 */
public class RBody {

    public boolean success=true;//是否成功
    public Object data;//返回的业务数据
    public String message;//返回的信息
    public Integer code=0;//返回的代码

    public RBody() {
        // TODO Auto-generated constructor stub
    }
    public RBody(String msg) {
        message=msg;
    }
    public RBody(Boolean success,String msg) {
        this.success=success;
        message=msg;
    }
    public RBody setRet(Boolean success,String msg){
        this.success = success;
        this.message = msg;
        return this;
    }
    public RBody setRet(Boolean success,String msg,Object data){
        this.success = success;
        this.message = msg;
        this.data = data;
        return this;
    }

    public static RBody success(String msg,Object data){
        RBody rb = new RBody();
        rb.success = true;
        rb.code = 0;
        rb.message = msg;
        rb.data = data;
        return rb;
    }

    public static RBody success(Object data) {
        return success(null, data);
    }

    public static RBody fail(Integer code,Object data,String message){
        RBody rb = new RBody();
        rb.success = false;
        rb.message = message;
        if(code != null && code == -1){
            code = 99999;
        }
        rb.code = code;
        rb.data = data;
        return rb;
    }
    public static RBody fail(Integer code,String message){
        return fail(code,null,message);
    }
    public static RBody fail(String message){
        return fail(99999, null, message);
    }
}
