package com.example.demo.main;

import java.lang.annotation.*;

/**
 * SOURCE（注解仅存在于源码中，在class字节码文件中不包含）
 * CLASS（默认的保留策略，注解会在class字节码文件中存在，但运行时无法获取）
 * RUNTIME（注解会在class字节码文件中存在，在运行时可以通过反射获取到）
 */
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.METHOD,
ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Yello {
    String name();
    int age() default 10;
    String[] habby();
}
