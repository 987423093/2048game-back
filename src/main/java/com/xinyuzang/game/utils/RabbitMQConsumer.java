package com.xinyuzang.game.utils;

import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @Author: zhoutao29203
 * @Date: 2020/11/9 14:28
 * @CopyRight: 2020 hundsun all rights reserved.
 */
@Component
@RabbitListener(queues = "queue")
public class RabbitMQConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Integer count = 0;

    @RabbitHandler
    public void consume(String str) {

        System.out.println(count++ + "Start Consume!" + str);
    }
}
