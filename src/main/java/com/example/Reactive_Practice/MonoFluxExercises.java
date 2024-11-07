package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.time.Duration;

public class MonoFluxExercises {
    public static void main(String[]args) throws InterruptedException {

//        Mono Practice
        Mono<String> mono = Mono.just("Hello World");
        mono.subscribe(System.out::println);

        Mono<String> mono2 = Mono.empty();
        mono2.subscribe(System.out::println);

        Mono<Integer> mono3 = Mono.error(new RuntimeException(" error occurred"));
        mono3.onErrorResume(e-> Mono.just(3)).subscribe(System.out::println);

        Mono<Integer> mono4 = Mono.just(10).map(n-> n*2);
        mono4.subscribe(System.out::println);

        Mono<String> mono5 = Mono.just("Hello and Welcome ").delayElement(Duration.ofSeconds(1));
        mono5.subscribe(System.out::println);
        Thread.sleep(3000);

        Mono<String> mono6 = Mono.just("User123")
                .flatMap(user-> Mono.just("user for " +user));
        mono6.subscribe(System.out::println);

//        Flux Practice
        Flux<Integer> flux1 = Flux.range(1, 10);
        flux1.subscribe(System.out::println);

        Flux<Object> flux2 = Flux.just("String", 123, true);
        flux2.subscribe(System.out::println);

        Flux<Integer> flux3 = Flux.range(1, 20);
        flux3.filter(n-> n%2 ==0).subscribe(System.out::println);

        Flux<String> flux4 = Flux.just("John", "Jane", "Tom");
        flux4.map(String::toUpperCase).subscribe(System.out::println);

        Flux<Integer> flux5 = Flux.just(1,2,3).concatWith(Flux.error(new RuntimeException("Oops!")));
        flux5.onErrorReturn(99).subscribe(System.out::println);

        Flux<Integer> fluxMerge1 =  Flux.range(1, 5);
        Flux<Integer> fluxMerge2 = Flux.range(6, 5);
        fluxMerge1.mergeWith(fluxMerge2).subscribe(System.out::println);

        Mono<String> names = Mono.just("Practice");
        Flux<String> hobbies = Flux.just("art" ,"dance", "sing", "play");
        Flux<String> combined = names.flatMapMany(n -> hobbies.map(hobby -> n + " " + hobby));
        combined.subscribe(System.out::println);

        Flux<Long> infiniteFlux = Flux.interval(Duration.ofMillis(500));
        infiniteFlux.limitRate(2).subscribe(System.out::println);
        Thread.sleep(5000);

        Flux<Integer> sub1 = Flux.range(1, 5);
        sub1.subscribe(System.out::println);
        sub1.map(n-> n*n).subscribe(System.out::println);

        Flux<String> fluxZip1 = Flux.just("alice", "bob", "kate");
        Flux<String> fluxZip2 = Flux.just("lily", "kristy", "Miha");
        Flux.zip(fluxZip1, fluxZip2).subscribe(System.out::println);

        Flux<String> log = Flux.just("apple", "banana", "mango", "berry");
        log.doOnNext(n -> System.out.println("logging: " + n)).subscribe(System.out::println);

        Flux<Integer> retryFlux = Flux.just(1, 2, 3)
                .concatWith(Flux.error(new RuntimeException("Error on 3")))
                .retry(2)
                .onErrorResume(e -> {
                    System.out.println("Retries exhausted, returning fallback value.");
                    return Flux.just(99);
                                });
        retryFlux.subscribe(System.out::println, error -> System.err.println("Final Error: " + error.getMessage()));


     }

}
