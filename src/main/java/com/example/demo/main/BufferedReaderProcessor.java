package com.example.demo.main;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Package: com.example.demo
 * @Description:函数式接口-读取字符
 * @Author: LuDeSong
 * @Date: 2021-3-17 11:04
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader br) throws IOException;
}
