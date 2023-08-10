package net.jkcodebase.springboot.controller;

import net.jkcodebase.springboot.publisher.RabbitMqProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private RabbitMqProducer producer;
    public MessageController(RabbitMqProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage (@RequestParam("message") String message){
        this.producer.sendMessage(message);
       return ResponseEntity.ok("Message sent to RabbitMq queue ...");
    }

}
