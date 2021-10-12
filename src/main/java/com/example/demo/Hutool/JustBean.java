package com.example.demo.Hutool;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;

import java.io.Serializable;

/**
 * @Package: com.example.demo.Hutool
 * @Description: 泛型浅克隆
 * @Author: LuDeSong
 * @Date: 2021-10-12 16:28
 */

public class JustBean extends CloneSupport<JustBean> implements Serializable
//        implements Cloneable<JustBean>
{
    private String name ;
    private JustBean justBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JustBean getJustBean() {
        return justBean;
    }

    public void setJustBean(JustBean justBean) {
        this.justBean = justBean;
    }

    //    @Override
//    public JustBean clone() {
//        try {
//            return (JustBean) super.clone();
//        } catch (CloneNotSupportedException e) {
//            throw new CloneRuntimeException(e);
//        }
//    }
}
