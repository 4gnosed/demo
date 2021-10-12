package com.example.demo.Hutool;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;

/**
 * @Package: com.example.demo.Hutool
 * @Description:
 * @Author: LuDeSong
 * @Date: 2021-10-12 16:07
 */

public class Practice {
    public static void main(String[] args) {
        cloneObject();


    }

    /**
     * 对象拷贝
     */
    private static void cloneObject() {
        JustBean justBean = new JustBean();
        justBean.setName("name");
        JustBean in = new JustBean();
        in.setName("nameIn");
        justBean.setJustBean(in);

        JustBean shallowClone = justBean.clone();
        JustBean deepClone = ObjectUtil.cloneByStream(justBean);
        JustBean fastJsonParse = JSON.parseObject(JSON.toJSONString(justBean), JustBean.class);
        in.setName("change");

        System.out.println(JSON.toJSONString(justBean));
        System.out.println(JSON.toJSONString(shallowClone));
        System.out.println(JSON.toJSONString(deepClone));
        System.out.println(JSON.toJSONString(fastJsonParse));
    }
}
