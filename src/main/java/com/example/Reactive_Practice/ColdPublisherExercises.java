package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class ColdPublisherExercises {
    public static void main(String[]args) throws InterruptedException{

//        Example 1
        Flux<Integer> flux1 = Flux.range(1, 10).delayElements(Duration.ofMillis(500));
        flux1.subscribe(n-> System.out.println("Subscriber 1: " +n));
        Thread.sleep(5000);

        flux1.subscribe(n-> System.out.println("Subscriber2: " +n));
        Thread.sleep(5500);

//        Example 2
        Flux<String> flux2 = Flux.just("apple", "banana", "berry", "kiwi");
        flux2.map(String::toUpperCase)
                .subscribe(s-> System.out.println("Uppercase words: " +s));

        flux2.map(String::length)
                .subscribe(s->System.out.println("length of words: " +s));
    }
}
