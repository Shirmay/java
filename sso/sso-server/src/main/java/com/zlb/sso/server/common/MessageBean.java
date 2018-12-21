package com.zlb.sso.server.common;

public class MessageBean<T> {

    private int code;

    private Object data;

    private String description;

    private static final int MESSAGE_INT_SUCCESS = 0;

    private static final int MESSAGE_INT_FAIL = -1;

    public static <T> MessageBean<T> success(){
        MessageBean<T> bean = new MessageBean<T>();
        bean.setCode(MESSAGE_INT_SUCCESS);
        bean.setDescription("success");
        return bean;
    }

    public static <T> MessageBean<T> success(String description){
        MessageBean<T> bean = new MessageBean<T>();
        bean.setCode(MESSAGE_INT_SUCCESS);
        bean.setDescription(description);
        return bean;
    }

    /**
     * 返回成功消息体
     * @param data 数据
     * @return
     */
    public static <T> MessageBean<T> success(T data, String description){
        MessageBean<T> bean = new MessageBean<T>();
        bean.setCode(MESSAGE_INT_SUCCESS);
        bean.setDescription(description);
        bean.setData(data);
        return bean;
    }

    /**
     * 返回失败消息体
     * @return
     */
    public static <T> MessageBean<T> fail(String description){
        MessageBean<T> bean = new MessageBean<T>();
        bean.setCode(-1);
        bean.setDescription(description);
        return bean;
    }

    /**
     * 返回失败消息体
     * @return
     */
    public static <T> MessageBean<T> fail(String description, int code){
        MessageBean<T> bean = new MessageBean<T>();
        bean.setCode(code);
        bean.setDescription(description);
        return bean;
    }

    public static String toJson(){
        return "";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
