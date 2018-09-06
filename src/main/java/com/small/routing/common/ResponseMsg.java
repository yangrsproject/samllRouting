package com.small.routing.common;

public class ResponseMsg {
    private int state;
    private Object msg;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public ResponseMsg() {

    }

    public ResponseMsg(int state, Object msg) {
        this.state = state;
        this.msg = msg;
    }
}
