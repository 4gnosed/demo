package com.example.demo.main;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author Gnosed Lu
 * @Date 2022/6/10
 * @Description
 */
public class MyClassCloader  extends java.lang.ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String s = name.replaceAll(".", System.lineSeparator()).concat(".class");
        byte[] bytes = null;
        try (FileInputStream is = new FileInputStream(s);
             ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            int b = 0;
            while ((b = is.read()) != 0) {
                os.write(b);
            }
            bytes = os.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
