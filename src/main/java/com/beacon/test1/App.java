package com.beacon.test1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("hello world");
        String responseStr = "1111";
        JSONObject object = JSON.parseObject(responseStr);

    }
}
