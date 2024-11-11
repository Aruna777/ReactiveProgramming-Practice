package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class BatchingOperatorExercise {
    public static void main(String[]args){

//        Buffer Example
        Flux.range(1, 10)
                .buffer(5)
                .doOnNext(System.out::println)
                .blockLast();

//        Buffer with Duration Example
        Flux.range(1, 10)
                .delayElements(Duration.ofMillis(300))
                .buffer(Duration.ofSeconds(2))
                .doOnNext(System.out::println)
                .blockLast();

//        Window Example
       Flux<String> flux1 =  Flux.just("colorado", "new york", "texas", "kansas", "oregon", "nevada", "florida" ,"washington", "california");
                flux1
                        .window(3)
                        .flatMap(i-> i.collectList())
                        .doOnNext(System.out::println)
                        .blockLast();

//        GroupBy Example
       Flux<Integer> flux2 = Flux.range(1, 10);
       flux2
               .groupBy(i-> i%2==0 ? "Even" : "Odd")
               .flatMap(n -> n.collectList()
                       .map(i -> "Group " + n.key() + ": " + i))
               .doOnNext(System.out::println)
               .blockLast();
    }

}
