package com.leyou.user.service;

import com.leyou.common.utils.NumberUtils;
import com.leyou.user.mapper.UserMapper2;
import com.leyou.user.pojo.User;
import com.leyou.user.utils.CodecUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Administrator
 * @Date: 2023/6/18 16:08
 * @Description:
 */
@Service
public class UserService2 {

    @Autowired
    private UserMapper2 userMapper2;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Boolean isExist(String value, String type) {
        User user = new User();
        if ("1".equals(type)) {
            user.setUsername(value);
        } else {
            user.setPhone(value);
        }
        return 1 == userMapper2.selectCount(user);
    }

    public void generalCode(String phone) {
        //生成随机6位数字
        String code = NumberUtils.generateCode(6);
        System.out.println(code);
        //缓存到redis,5分钟过期
        stringRedisTemplate.opsForHash().put("sms_register_code", phone, code);
        stringRedisTemplate.expire("sms_register_code", 5, TimeUnit.MINUTES);
        //生成消息推送到短信服务
        Map map = new HashMap<String, String>();
        map.put("phone", phone);
        map.put("code", code);
        rabbitTemplate.convertAndSend("leyou.sms.exchange", "leyou.sms.queue", map);
    }

    public ResponseEntity<String> register(String username, String password, String confirmPassword, String phone, String code) {
        //判断用户名是否存在；
        if (isExist(username, "1")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("用户名已存在！");
        }
        //判断手机号是否存在；
        if (isExist(phone, "2")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("手机号已存在！");
        }
        //判断两个密码是否一致；
        if (!password.equals(confirmPassword)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("设置密码前后不一致！");
        }
        //判断验证码
        String value= stringRedisTemplate.opsForHash().get("sms_register_code", phone).toString();
        //有手机号key，验证码有效，判断是否一样；
        if (value != null) {
            if (value.equals(code)) {
                //插入用户，注意密码需要加密存入数据库
                User user = new User();
                user.setPhone(phone);
                user.setUsername(username);
                user.setCreated(new Date());
                user.setId(null);
                String salt = CodecUtils.generateSalt();
                user.setSalt(salt);
                user.setPassword(CodecUtils.md5Hex(password, salt));
                userMapper2.insert(user);
                return ResponseEntity.status(HttpStatus.CREATED).body("注册成功！");
            } else {
                //返回验证码错误响应
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("验证码错误！");
            }
            //没有手机号key，验证码无效，显示验证码过期或者错误；
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("验证码过期或者错误！");
        }

    }
}
