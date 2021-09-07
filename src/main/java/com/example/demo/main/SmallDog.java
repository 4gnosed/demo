package com.example.demo.main;

import lombok.Data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @Package: com.example.demo
 * @Description:
 * @Author: LuDeSong
 * @Date: 2020-12-18 9:14
 */

@Data
public class SmallDog extends Dog implements Serializable {
    private static final long serialVersionUID = 1L;

    String name;
    Integer age;
    transient Double price;
    static String s;


    @Override
    public void eat() {
        System.out.println("small dog............");
    }

    @Override
    public void eating() {
        System.out.println("small dog111111111");
    }

    private void writeObject(ObjectOutputStream out){
        try {
            ObjectOutputStream.PutField putField = out.putFields();
            System.out.println("name:" + name);
            putField.put("name",name+"1111111111");
            putField.put("age",age);
            out.writeFields();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readObject(ObjectInputStream in){
        try {
            ObjectInputStream.GetField getField = in.readFields();
            Object name = getField.get("name", "fjsdlfds");
            Object age = getField.get("age", "");
            this.age= (Integer) age;
            this.name= (String) name;
            System.out.println("\nname:" + name+" , age:" + age);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
