package com.example.demo;

/**
 * @Package: com.example.demo
 * @Description:
 * @Author: LuDeSong
 * @Date: 2021-3-3 15:54
 */

@Yello(name = "Test", habby = {})
public class Test {
    @White(name = "juestSay")
    void justSay(String msg) {
        System.out.println("Just say " + msg);
    }
    void justSaying(String msg) {
        System.out.println("Just saying " + msg);
    }
}
