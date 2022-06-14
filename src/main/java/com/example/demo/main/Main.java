package com.example.demo.main;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Package: com.example.demo
 * @Description:
 * @Author: LuDeSong
 * @Date: 2021-1-12 8:48
 */

public class Main {
     int i =1;
     long j =1;
    public static void main(String[] args) {
        final Main main = new Main();
        System.out.println(ClassLayout.parseInstance(main).toPrintable());
    }
}
