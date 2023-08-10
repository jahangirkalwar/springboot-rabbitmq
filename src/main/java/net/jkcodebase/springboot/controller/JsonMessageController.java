package net.jkcodebase.springboot.controller;

import net.jkcodebase.springboot.dto.User;
import net.jkcodebase.springboot.publisher.RabbitMqJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class JsonMessageController {

    private RabbitMqJsonProducer jsonProducer;
    public JsonMessageController(RabbitMqJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage (@RequestBody User user){
            jsonProducer.sendJsonMessage(user);
            return ResponseEntity.ok("Json Message send to RabbitMQ ...");
    }




}
