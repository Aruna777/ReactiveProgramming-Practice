package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ConversionExercise {

    public static void main (String[] args){

//        Example 1
        Mono<String> conversion1 = Mono.just("Hello!");
        Flux<String> flux = conversion1.flux();
        flux.subscribe(System.out::println);

//        Example 2
        Flux<Integer> conversion2 = Flux.just(1, 2, 3, 4, 5);
        Mono<Integer> mono = conversion2.next();
        mono.subscribe(System.out::println);

//        Example 3
        Flux<Integer> conversion3 = Flux.just(1, 2, 3, 4, 5);
        Mono<Integer> ConversionMono =  conversion3.next();
        Flux<Integer> BackToFlux = ConversionMono.flux().map(n-> n*2);
        Flux<Integer> combined = Flux.merge(BackToFlux, conversion3 );
        combined.subscribe(System.out::println);

//        Example 4
        Flux<String> flux1 = Flux.just("Red", "Blue", "Green");
        Mono<String> firstMono = flux1.next().map(String::toUpperCase);
        firstMono.subscribe(System.out::println);

//        Example 5
        Mono<String> mono3 = Mono.just("Start");
        Flux<String> flux3 = Flux.just("Middle", "End");
        Flux<String> combinedFlux = Flux.concat(mono3.flux(), flux3);
        combinedFlux.subscribe(System.out::println);

    }
}
