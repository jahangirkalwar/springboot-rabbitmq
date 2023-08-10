package net.jkcodebase.springboot.consumer;

import net.jkcodebase.springboot.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
    public void consumeJsonMessage (User user){
        LOGGER.info(String.format("Message Received -> %s", user.toString()));
    }
}
