package com.csiris.springboot.config;

import java.time.Duration;
import java.util.Random;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.EnableScheduling;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import model.Stock;


@Configuration
@EnableScheduling
public class ReactiveConfig {
	
    //private static final Logger log = LoggerFactory.getLogger(ReactiveConfig.class);
	
    @Value("${config.producer.upperBound}")
	private Integer upperBound;
	
    @Bean
    public Sinks.Many<String> sink(){
        //last 5 generated elements are cached and replayed upon new subscription..
        return Sinks.many().replay().limit(5);
    }
	
    @Bean("fluxpublisher")
    public Flux<Stock> publisher(Supplier<Stock> supplier){
         return Flux.range(0, upperBound)
		.concatMap(i->Flux
		.just(1)
		.delayElements(Duration.ofSeconds(1))
		.map(s -> Stock.of(supplier.get()))
		.publish()
		.refCount()
	 );
    }
	
//  @Bean("fluxpublisher2")
//  public Flux<Stock> publisher2(Supplier<Stock> supplier){
//	log.warn(supplier.get().getValue()+"");
//      return Flux.defer(() -> Flux.just(new Stock(rnd().nextDouble())));
//  }
	
    @Bean
    @Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
    public Stock stock() {
        return new Stock(rnd().nextDouble());
    }
   
    @Bean
    public Supplier<Stock> supplier () {
        return this::stock;
    }
	
    @Bean
    public Random rnd () {
        return new Random();
    }
}
