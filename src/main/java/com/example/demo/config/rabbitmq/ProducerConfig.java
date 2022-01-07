package com.example.demo.config.rabbitmq;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

  @Value("${app.rabbitmq.producers.queue.meta.name}")
  private String queue;

  @Bean
  public Queue queue() {
    return new Queue(queue, true);
  }

  @Bean
  Binding binding(Queue queue, Exchange delayExchange) {
    return BindingBuilder.bind(queue).to(delayExchange).with("meta-key").noargs();
  }

  @Bean
  CustomExchange delayExchange() {
    Map<String, Object> args = new HashMap<>();
    args.put("x-delayed-type", "direct");
    return new CustomExchange("meta-exchange", "x-delayed-message", true, false, args);
  }
}
