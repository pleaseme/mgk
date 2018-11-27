package com.example.jpademo.common;

import com.fasterxml.jackson.databind.ObjectMapper;


public class CRM_resultt {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer code;

    private Integer count;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    CRM_resultt(Integer code, String msg,Integer count,Object data){
        this.code =code;
        this.msg = msg;
        this.count=count;
        this.data = data;
    }


    public static CRM_resultt build(Integer code, String msg,Integer count,Object data) {
        return new CRM_resultt(code,msg,count,data);
    }

    public static CRM_resultt ok(Object data) {
        return new CRM_resultt(data);
    }

    public static CRM_resultt ok() {
        return new CRM_resultt(null);
    }

    public CRM_resultt() {

    }

    public static CRM_resultt build(Integer code, String msg) {
        return new CRM_resultt(code, msg, null);
    }

    public CRM_resultt(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CRM_resultt(Object data) {
        this.code = 200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.code == 200;
//    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    public Integer getCount (){
        return count;
    }


}
