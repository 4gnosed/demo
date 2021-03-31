package com.example.demo;

/**
 * @Package: com.example.demo
 * @Description:
 * @Author: LuDeSong
 * @Date: 2021-1-12 8:48
 */

public class Main {
    public static void main(String[] args) {
        String s = "abc";
        switch (s){
            case "abc":System.out.println(0);break;
            case "bda":System.out.println(1);break;
            case "cda":System.out.println(2);break;
            default:System.out.println("defffffffault");
        }
    }
}
