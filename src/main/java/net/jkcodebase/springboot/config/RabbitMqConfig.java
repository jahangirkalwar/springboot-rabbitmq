package net.jkcodebase.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key.name}")
    private String routingKey;

    @Value("${rabbitmq.json.queue.name}")
    private String jsonQueue;

    @Value("${rabbitmq.json.routing.key.name}")
    private String jsonRoutingKey;


    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }


    //binding between queue and exchange with routing key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Binding jsonBinding(){
            return BindingBuilder.bind(jsonQueue())
                    .to(exchange())
                    .with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter messageConverter (){
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate  amqpTemplate (ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate =  new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    /*These are the beans required for spring boot application to work with RabbitMq,
     but spring boot autoconfiguration will automatically configure these three beans for us.*/

    //  1. ConnectionFactory
    //  2. RabbitTemplate
    //  3. RabbitAdmin


}
