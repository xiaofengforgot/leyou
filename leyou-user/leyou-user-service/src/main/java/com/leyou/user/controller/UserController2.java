package com.leyou.user.controller;

import com.leyou.user.service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zzf
 * @Date: 2023/6/18 15:48
 * @Description:
 */
@RestController("/user")
public class UserController2 {

    @Autowired
    private UserService2 userService2;

    @GetMapping("/check2/{value}/{type}")
    public ResponseEntity<Boolean> check(@PathVariable String value, @PathVariable String type) {
        //手机，查询数据库是否有这个手机号；
        //用户名，查询数据库是否有这个手机号；
        Boolean flag = userService2.exist(value, type);
        return ResponseEntity.ok(flag);
    }

    @PostMapping("/code2")
    public ResponseEntity<Void> generalCode(String phone) {
        try {
            userService2.generalCode(phone);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }
}
