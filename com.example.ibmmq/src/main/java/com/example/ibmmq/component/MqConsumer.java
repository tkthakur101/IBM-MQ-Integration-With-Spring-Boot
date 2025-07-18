package com.example.ibmmq.component;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MqConsumer {

    @JmsListener(destination = "${ibm.mq.queue}")
    public void receive(String message) {
        System.out.println("Received from MQ: " + message);
    }
}
