package com.csiris.springboot;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csiris.springboot.service.ObjectPublisher;

import model.Stock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@CrossOrigin
@RestController
public class StockController {

   private final ObjectPublisher publisher;
   private final Sinks.Many<String> sink;
   
   public StockController(ObjectPublisher publisher,Sinks.Many<String> sink) {
      this.publisher = publisher;
      this.sink = sink;
   }
   
   @GetMapping(value = "/stream2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
   public Flux<Stock> getStocks2(){
       return publisher.getPublisher().log("Published..!");
   }
   
   @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
   public Flux<String> getStocks(){
	   //return Flux.just("OK.");
	   return sink.asFlux();
   }

}