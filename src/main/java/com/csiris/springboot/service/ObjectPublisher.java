package com.csiris.springboot.service;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import model.Stock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@Component
public class ObjectPublisher {
	
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final Sinks.Many<String> sink;
   private final Flux<Stock> publisher;
   private final Random random;
   
   
   public Flux<Stock> getPublisher() {
       return publisher;
   }

   public ObjectPublisher(Many<String> sink,Flux<Stock> publisher,Random random) {
       this.sink = sink;
       this.publisher = publisher;
       this.random = random;
   }

   @Scheduled(fixedRate = 1000)
   public void publish(){
       String data = Integer.toString(random.nextInt());
       log.info("new String data , value: {}", data);
       this.sink.tryEmitNext(data);
   }
}
