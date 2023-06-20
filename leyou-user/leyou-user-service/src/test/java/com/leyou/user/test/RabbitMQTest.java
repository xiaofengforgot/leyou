package com.leyou.user.test;

import com.leyou.user.LeyouUserApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Administrator
 * @Date: 2023/6/19 21:25
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LeyouUserApplication.class)
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testRedis() {
        //生成消息推送到短信服务
        Map map = new HashMap<String, String>();
        map.put("phone", "110");
        map.put("code", "888888");
        rabbitTemplate.convertAndSend("leyou.sms.exchange", "sms.verify.code", map);
    }
}
