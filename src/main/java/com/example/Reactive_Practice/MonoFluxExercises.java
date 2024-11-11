package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.time.Duration;

public class MonoFluxExercises {

//    Mono Practice
    public Mono<String> getHelloWorldMono() {
        return Mono.just("Hello World");
    }

    public  Mono<String> getEmptyMono(){
        return Mono.empty();
    }

    public Mono<Object> getErrorMono() {
        return Mono.error(new RuntimeException("error occurred"))
                .onErrorResume(e -> Mono.just(3));
    }

    public Mono<Integer> getMappedMono() {
        return Mono.just(10).map(n -> n * 2);
    }

    public Mono<String> getDelayedMono(){
        return Mono.just("Hello and Welcome ").delayElement(Duration.ofSeconds(1));
    }

    public Mono<String> getFlatMappedMono(){
        return Mono.just("User123")
                .flatMap(user-> Mono.just("user for " +user));
    }

//    Flux Practice
    public Flux<Integer> getRangeFlux(){
        return  Flux.range(1, 10);
    }

    public Flux<Object> getMixedFlux(){
        return Flux.just("String", 123, true);
    }

    public Flux<Integer> getFilteredFlux(){
        return Flux.range(1, 20)
                .filter(n-> n%2 ==0);
    }

    public Flux<String> getMappedFlux(){
        return Flux.just("John", "Jane", "Tom")
                .map(String::toUpperCase);
    }

    public Flux<Integer> getErrorHandledFlux(){
        return Flux.just(1,2,3)
                .concatWith(Flux.error(new RuntimeException("Oops!")))
                .onErrorReturn(99);
    }

    public Flux<Integer> getMergedFlux(){
        Flux<Integer> flux1 =  Flux.range(1, 5);
        Flux<Integer> flux2 = Flux.range(6, 5);
        return  flux1.mergeWith(flux2);
    }

    public Flux<String> getCombinedNamesAndHobbies() {
        Mono<String> names = Mono.just("Practice");
        Flux<String> hobbies = Flux.just("art", "dance", "sing", "play");
        return names.flatMapMany(n -> hobbies.map(hobby -> n + " " + hobby));
    }

    public static Flux<Long> getLimitedRateInfiniteFlux() {
        return Flux.interval(Duration.ofMillis(500)).limitRate(2);
    }

    public static Flux<Integer> getSquaredFlux() {
        return Flux.range(1, 5).map(n -> n * n);
    }

    public Flux<String> getZippedNamesFlux() {
        Flux<String> flux1 = Flux.just("alice", "bob", "kate");
        Flux<String> flux2 = Flux.just("lily", "kristy", "Miha");
        return Flux.zip(flux1, flux2, (name1, name2) -> name1 + " & " + name2);
    }

    public Flux<String> getLoggedFlux() {
        return Flux.just("apple", "banana", "mango", "berry")
                .doOnNext(n -> System.out.println("logging: " + n));
    }

    public Flux<Integer> getRetryFlux() {
        return Flux.just(1, 2, 3)
                .concatWith(Flux.error(new RuntimeException("Error on 3")))
                .retry(2)
                .onErrorResume(e -> Flux.just(99));
    }

}
