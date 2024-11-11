package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

public class RepeatRetryOperatorsExercise {
    public static void main(String[]args) {

//        Repeat Example
        Flux<Integer> flux1 = Flux.range(1, 5);
        flux1
                .repeat(2)
                .subscribe(System.out::println);

//        RepeatWhen Example
        Flux<String> flux2 = Flux.just("A", "B", "C");
        flux2
                .repeatWhen(i-> i.take(3))
                .subscribe(System.out::println);

//        Retry Example
        Flux<Integer> flux3 = Flux.range(1, 3)
                .map(i->{
                    if(i==3) throw new RuntimeException("Error at 3");
                    return i;
                }).retry(2);
        flux3
                .subscribe(System.out::println);

//        Retry Example
        Flux<Integer> flux4 = Flux.range(1, 10)
                .map(i -> {
                    if (i == 5) throw new RuntimeException("Error at 5");
                    return i;
                })
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(1)));
        flux4
                .subscribe(
                System.out::println,
                error -> System.out.println("Final Error: " + error.getMessage())
        );


    }
}
