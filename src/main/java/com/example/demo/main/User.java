package com.example.demo.main;

import lombok.*;

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
@NoArgsConstructor
public class User {
    String name;
    String phone;
    String address;
    Long scope;
    User user;
}
