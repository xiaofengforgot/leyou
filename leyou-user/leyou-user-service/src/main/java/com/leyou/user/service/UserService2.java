package com.leyou.user.service;

import com.leyou.common.utils.NumberUtils;
import com.leyou.user.mapper.UserMapper2;
import com.leyou.user.pojo.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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

    public Boolean exist(String value, String type) {
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
        //缓存到redis,5分钟过期
        stringRedisTemplate.opsForHash().put("sms_register_code", phone, code);
        stringRedisTemplate.expire("sms_register_code", 5, TimeUnit.MINUTES);
        //生成消息推送到短信服务
        Map map = new HashMap<String, String>();
        map.put("phone", phone);
        map.put("code", code);
        rabbitTemplate.convertAndSend("leyou.sms.exchange", "leyou.sms.queue", map);
    }
}
