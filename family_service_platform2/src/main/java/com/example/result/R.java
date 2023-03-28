package com.example.result;

/**
 * 用于响应给前端数据
 */
public class R {
    private Integer code = 200;
    private String message = "";
    private Object result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public R(Integer code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public R() {
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
