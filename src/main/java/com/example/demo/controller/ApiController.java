package com.example.demo.controller;

import com.example.demo.service.rabbitmq.RabbitmqProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Log4j2
@Controller
@EnableWebMvc
@RequiredArgsConstructor
public class ApiController {

  private final RabbitmqProducerService rabbitmqProducerService;

  @GetMapping(value = "/api/test")
  public ResponseEntity<HttpStatus> test(@RequestBody String message) {
    rabbitmqProducerService.sendMetaQueue(message);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
