package com.example.demo.main;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @Package: com.example.demo
 * @Description:
 * @Author: LuDeSong
 * @Date: 2021-6-3 11:07
 */

@Data
@AllArgsConstructor
@Builder
@ToString
public class User {
    String name;
    String phone;
    String address;
    Long scope;
    User user;
}
