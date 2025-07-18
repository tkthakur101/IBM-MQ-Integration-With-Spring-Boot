package com.example.ibmmq.controller;


import com.example.ibmmq.service.MqProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq")
public class MqController {

    private final MqProducer producer;

    public MqController(MqProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public String send(@RequestParam String message) {
        producer.sendMessage(message);
        return "Message sent: " + message;
    }
}