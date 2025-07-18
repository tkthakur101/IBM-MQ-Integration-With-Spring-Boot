package com.example.ibmmq.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MqProducer {

    private final JmsTemplate jmsTemplate;
    private final String queueName;

    public MqProducer(JmsTemplate jmsTemplate,
                      @Value("${ibm.mq.queue}") String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend(queueName, message);
    }
}