package com.example.demo.service.rabbitmq;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
@Log4j2
public class RabbitmqProducerService {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  public void sendMetaQueue(String msg) {
    MessageProperties messageProperties = new MessageProperties();
    messageProperties.setHeader("x-delay", 3000);
    Message message = new Message(msg.getBytes(), messageProperties);
    rabbitTemplate.convertAndSend("meta-exchange", "meta-key", message);
  }
}
