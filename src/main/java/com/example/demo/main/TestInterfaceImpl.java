package com.example.demo.main;

/**
 * @Package: com.example.demo
 * @Description:
 * @Author: LuDeSong
 * @Date: 2021-3-3 15:36
 */

public class TestInterfaceImpl implements TestInterface {
    @Override
    public void sayWhat() {
        System.out.println("Iam saying...");
    }

    @Override
    public void sayHello(String msg) {
        System.out.println("Iam Hello..." + msg);
    }
}
