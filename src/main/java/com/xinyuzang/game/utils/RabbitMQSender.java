package com.xinyuzang.game.utils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.impl.AMQConnection;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: zhoutao29203
 * @Date: 2020/11/9 15:23
 * @CopyRight: 2020 hundsun all rights reserved.
 */
@Component
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private String mqMessage = "message";

    /**
     * 发送direct消息
     */
    public void sendDirect() {

        rabbitTemplate.convertAndSend("directExchange", "routingKey", mqMessage);
    }

    /**
     * 发送topic消息
     */
    public void sendTopic(String routingKey) {

        rabbitTemplate.convertAndSend("topicExchange", routingKey, mqMessage + ":" + routingKey);
    }


}
