package com.xinyuzang.game.utils;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: zhoutao29203
 * @Date: 2020/11/9 15:31
 * @CopyRight: 2020 hundsun all rights reserved.
 */
@Configuration
public class MQFactory {

    @Bean
    public Queue createQueue() {
        return new Queue("queue");
    }

    @Bean
    public Exchange createDirectExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Exchange createTopicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Exchange createFanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding createDirectBinding() {
        return BindingBuilder.bind(createQueue()).to(createDirectExchange()).with("routingKey").noargs();
    }

    @Bean
    public Binding createTopicBinding() {
        return BindingBuilder.bind(createQueue()).to(createTopicExchange()).with("topic.#").noargs();
    }


}
