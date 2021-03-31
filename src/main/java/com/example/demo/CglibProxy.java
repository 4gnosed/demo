package com.example.demo;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Package: com.example.demo
 * @Description:Cglib动态代理
 * @Author: LuDeSong
 * @Date: 2021-3-3 15:55
 */

public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("前置代理");
        Object result = proxy.invokeSuper(o,args);
        System.out.println("后置代理");
        return result;
    }
}
