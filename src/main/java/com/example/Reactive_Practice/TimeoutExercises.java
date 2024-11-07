package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class TimeoutExercises {
    public static void main(String[]args) throws InterruptedException{

//        Example 1
        Flux<Long> flux = Flux.interval(Duration.ofMillis(500))
                .timeout(Duration.ofMillis(300))
                .doOnError(e -> System.out.println("Timeout occurred: " + e.getMessage()));
        flux.subscribe(
                System.out::println,
                Throwable::printStackTrace);
        Thread.sleep(3000);

//        Example 2
        Mono<String> mono1 = Mono.just("Hello").delayElement(Duration.ofSeconds(2));
        mono1
                .timeout(Duration.ofSeconds(1))
                .onErrorResume(throwable -> Mono.just("Welcome"))
                .subscribe(System.out::println);
        Thread.sleep(3000);

//        Example 3
        Flux<Integer> flux2 = Flux.range(1, 10)
                .delayElements(Duration.ofMillis(10));
        flux2.timeout(Duration.ofSeconds(10))
                .retry(2)
                .subscribe(System.out::println);
        Thread.sleep(4000);

//        Example 4
        Flux<Long> flux3 = Flux.interval(Duration.ofMillis(800));
        flux3.take(3)
                .concatWith(Flux.interval(Duration.ofMillis(800))
                        .timeout(Duration.ofMillis(600)))
                .doOnError(e-> System.out.println("error occurred: " +e.getMessage()))
                .subscribe(System.out::println);
        Thread.sleep(4000);
    }
}
