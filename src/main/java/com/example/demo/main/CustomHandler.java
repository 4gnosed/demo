package com.example.demo.main;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomHandler implements InvocationHandler {

    Object target;

    CustomHandler(Object target){
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("pre.......");
        Object result = method.invoke(target, args);
        System.out.println("after.......");
        return result;
    }
}
