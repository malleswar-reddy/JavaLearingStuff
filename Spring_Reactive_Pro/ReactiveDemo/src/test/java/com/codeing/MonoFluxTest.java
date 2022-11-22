package com.codeing;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
        Mono<?> monoString= Mono.just("Coding")
                            .then(Mono.error( new RuntimeException("Exception occurred")))
                            .log();
        monoString.subscribe(System.out::println, e-> System.out.println(e));
    }

    @Test
    public void  testFlux(){
        Flux<String> stringFlux= Flux.just("Coding","Java","Hello")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Error occurred")))
                .log();
        stringFlux.subscribe(System.out::println,e-> System.out.println(e));
    }
}
