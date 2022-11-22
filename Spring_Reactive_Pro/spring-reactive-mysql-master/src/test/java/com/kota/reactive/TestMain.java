package com.kota.reactive;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestMain {

    @Test
    public void handleVoidsInMonos_works() {
        Mono<Void> monoThatDoesSomething = Mono.defer(() -> Mono.empty());
        Object objectReturned = new Object();

        Mono<Object> monoThatReturnsSomething = Mono.defer(() -> Mono.just(objectReturned));

        List<Mono<?>> monoCollection = Arrays.asList(
                monoThatReturnsSomething,
                monoThatDoesSomething.then(Mono.just(new Object()))
        );

        Mono<Object> zipped = Mono.zip(monoCollection, objects -> objects[0]);
        StepVerifier.create(zipped)
                .expectNextMatches(obj -> obj == objectReturned)
                .verifyComplete();
    }

}
