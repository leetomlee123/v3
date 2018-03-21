package com.lx.backstagemanagement.RabbitMqProduct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleProduct {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sender(String info) {
        amqpTemplate.convertAndSend("hello", info);
    }
}
