package com.example.demo.service.rabbitmq;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@EnableRabbit
public class RabbitmqConsumerService {

  @RabbitListener(queues = {"${app.rabbitmq.consumers.queue.meta.name}"})
  public void receiveMetaMessage(@Payload String message) {
    log.info(message);
  }
}
