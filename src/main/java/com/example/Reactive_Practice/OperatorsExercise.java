package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

public class OperatorsExercise {

    public static void main(String[]args) throws InterruptedException{

//        StartsWith Example
        Flux<String> flux1 = Flux.just("E1", "E2", "E3", "E4", "E5");
        flux1.startWith("Employee ID " )
                .subscribe(System.out::println);

        Mono<String> mono1 = Mono.just("User");
        mono1
                .flux()
                .startWith("Admin")
                .subscribe(System.out::println);

//        ConcatWith Example
        Flux<String> flux2 = Flux.just("Apple", "berry", "kiwi");
        Flux<String> flux3 = Flux.just("banana", "mango", "cherry");
        Flux<String> combined = flux2.concatWith(flux3);
        combined.subscribe(System.out::println);

        Mono<Integer> mono2 = Mono.just(1);
        Mono<Integer> mono3 = Mono.just(2);
        Flux<Integer> combined2 = mono2.concatWith(mono3);
        combined2.subscribe(System.out::println);

//        ConcatDelayError Example
        Flux<Integer> flux4 = Flux.error(new RuntimeException("Error occurred: "));
        Flux<Integer> flux5 = Flux.range(1, 5);
        Flux<Integer> combined3 = flux4.concatWith(flux5);
        combined3
                .concatMapDelayError(Flux::just)
                .subscribe(System.out::println, error -> System.out.println("Caught error: " + error.getMessage()));

//        Merge Example
        Flux<String> flux6 = Flux.interval(Duration.ofMillis(100)).map(i-> "Source 1: " +i).take(3);
        Flux<String> flux7 = Flux.interval(Duration.ofMillis(100)).map(i->"Source 2: " +i).take(3);
         flux6.mergeWith(flux7).subscribe(System.out::println);
        Thread.sleep(1000);

//        Zip Example
        Flux<String> flux8 = Flux.just("product1", "product2");
        Flux<Integer> flux9 = Flux.just(1, 2);
        Flux<Tuple2<String, Integer>> combined4 = Flux.zip(flux8, flux9);
        combined4.subscribe(System.out::println);

//        Flatmap Example
        Flux<Integer> numbers = Flux.range(1,  5);
        Flux<String> result = numbers.flatMap(i -> Flux.just("Value: " + i));
        result.subscribe(System.out::println);

        Mono<String> mono4 = Mono.just("Hello");
        Mono<String> result2 = mono4.flatMap(i-> Mono.just("Mono Value: " +i));
        result2.subscribe(System.out::println);

//        FlatmapMany Example
        Mono<String> mono5 = Mono.just("Hello");
        mono5.flatMapMany(i-> Flux.just("Order1", "Order2", "Order3" ))
                .subscribe(System.out::println);

//        ConcatMap Example
        Flux<String> userIds = Flux.just("U1", "U2");
        userIds.concatMap(id -> Mono.just("Details for " + id))
                .subscribe(System.out::println);

    }
}
