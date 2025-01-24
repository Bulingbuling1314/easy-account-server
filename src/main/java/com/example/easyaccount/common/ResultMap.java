package com.example.easyaccount.common;

import java.util.HashMap;

public class ResultMap extends HashMap<String, Object> {

    public ResultMap() {
        put("code", 200);
    }

    public static ResultMap ok(int code, Object data) {
        ResultMap r = new ResultMap();
        r.put("code", code);
        r.put("data", data);
        return r;
    }

    public static ResultMap ok(Object data) {
        ResultMap r = new ResultMap();
        r.put("data", data);
        return r;
    }

    public static ResultMap ok(String msg) {
        ResultMap r = new ResultMap();
        r.put("data", msg);
        return r;
    }

    public static ResultMap error(int code, String msg) {
        ResultMap r = new ResultMap();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResultMap error() {
        return error(500, "获取数据有误，请检查数据填写是否完整!");
    }

    public static ResultMap error(String msg) {
        return error(500, msg);
    }


}
