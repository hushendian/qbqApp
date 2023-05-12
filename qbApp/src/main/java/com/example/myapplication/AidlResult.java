package com.example.myapplication;

public class AidlResult {

    /**
     * code : 0
     * error :
     * msg : {"dwdm":"3701004200003","dwlx":"1","dwlxmc":"营业性","dwmc":"济南公司"}
     */

    private int code;
    private String error;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
