package com.leyou.user.api;

import com.leyou.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Administrator
 * @Date: 2020/2/12 15:24
 * @Description:
 */
public interface UserApi {

    @GetMapping("query")
    public User queryUser(@RequestParam("username")String username, @RequestParam("password")String password);
}
