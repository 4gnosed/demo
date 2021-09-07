package com.example.demo.main;

import lombok.Data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @Package: com.example.demo
 * @Description:
 * @Author: LuDeSong
 * @Date: 2020-12-18 9:13
 */

@Data
public class Dog extends Animal
        implements Externalizable
{

    private static final long serialVersionUID = 1L;

    String name;
    transient Integer age;   //transient 对 实现Externalizable接口的类不起作用
    Integer rank;
    Double price;


    public void eat() {
        System.out.println("Dog");
    }
    @Override
    public void eating() {
        System.out.println("Dog.........");
    }

    /**
     * writeExternal 和readExternal 字段顺序要一致
     * @param out
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
        out.writeInt(rank);
        out.writeDouble(price);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
        rank = in.readInt();
        price = in.readDouble();
    }
}
