package net.jkcodebase.springboot.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

    @Value("${rabbitmq.routing.key.name}")
    private String routingKey;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    private RabbitTemplate rabbitTemplate;

    public RabbitMqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Message Sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }

}
