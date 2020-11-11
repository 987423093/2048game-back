package com.xinyuzang.game;

import com.xinyuzang.game.utils.RabbitMQConsumer;
import com.xinyuzang.game.utils.RabbitMQSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private RabbitMQSender rabbitMQSender;
    @Autowired
    private RabbitMQConsumer rabbitMQConsumer;

    @Test
    void contextLoads() {

//        for (int i = 1; i <= 10; i++) {
//			rabbitMQSender.sendDirect();
            rabbitMQSender.sendTopic("topic.1");
//            rabbitMQSender.sendTopic("topic.2");
//            rabbitMQSender.sendTopic("noTopic.1");
//        }

    }

}
