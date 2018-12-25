package com.zlb.sso.server.vo;

public class ResponseData {

    public static final int SUCCESS_CODE = 0;

    public static final int FAIL_CODE = -1;

    private int code;

    private String message;

    private Object data;

    public static ResponseData success(String message) {
        ResponseData data = new ResponseData();
        data.setCode(ResponseData.SUCCESS_CODE);
        data.setMessage(message);
        return data;
    }

    public static ResponseData faill(String message) {
        ResponseData data = new ResponseData();
        data.setCode(ResponseData.FAIL_CODE);
        data.setMessage(message);
        return data;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
