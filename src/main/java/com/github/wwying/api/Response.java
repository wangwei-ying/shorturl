package com.github.wwying.api;

import org.springframework.http.HttpStatus;

public class Response<T> {

    private int status;

    private T data;

    private String msg;
    private Response(){

    }

    private Response(int status,T data){
        this.status = status;
        this.data = data;
    }

    private Response(int status,T data,String msg){
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static <T> Response ApiResponse(int status, T data){
        return new Response<T>(status,data);
    }
    public static <T> Response ApiSuccess(T data){
        return new Response<T>(HttpStatus.OK.value(),data);
    }
    public static Response ApiError(String msg){
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),null,msg);
    }
}
