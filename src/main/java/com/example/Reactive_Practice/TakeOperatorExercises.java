package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class TakeOperatorExercises {
    public static void main(String[] args) throws InterruptedException{

//  Exercise 1
        Flux<Integer> flux1 = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        flux1
                .take(5)
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        ()-> System.out.println("Completed flux1"));

//  Exercise 2
        Flux<Integer> flux2 = Flux.just(5, 6, 7 , 8, 9, 10, 20, 30, 40, 50);
        flux2
                .takeWhile(n-> n<30)
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        ()-> System.out.println("Completed flux2"));

//  Exercise 3
        Flux<Integer> flux3 = Flux.range(10, 50);
        flux3
                .takeUntil(n-> n==20)
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        ()-> System.out.println("Completed flux3") );

//  Exercise 4
        Flux<Integer> flux4 = Flux.range(10, 50);
        flux4
                .take(30)
                .takeWhile(n-> n<=20)
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        ()-> System.out.println("Completed flux4"));

//  Exercise 5
        Flux<String> flux5 = Flux.just("Apple", "Orange", "Grape", "Mango", "Banana", "Cherry");
        flux5
                .takeUntil(n-> n.startsWith("M"))
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        ()-> System.out.println("Completed flux5"));

//  Exercise 6
        Flux<Integer> flux6 = Flux.range(1, 100);
        flux6
                .delayElements(Duration.ofMillis(100))
                .take(20)
                .subscribe(System.out::println,
                        Throwable::printStackTrace,
                        ()-> System.out.println("Completed flux6"));

        Thread.sleep(10000);
    }

}
