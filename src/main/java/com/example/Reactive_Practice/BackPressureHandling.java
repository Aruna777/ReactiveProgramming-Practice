package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class BackPressureHandling {
    public static void main(String[]args) throws InterruptedException{

//        .onBackpressureBuffer Example
        Flux.interval(Duration.ofMillis(10))
                .onBackpressureBuffer(50,  value -> System.out.println("Buffer Overflow for value: " + value))
                .doOnNext(value -> System.out.println("Produced: " + value))
                .delayElements(Duration.ofMillis(100))
                .subscribe(value -> System.out.println("Consumed: " + value),Throwable::printStackTrace);
        Thread.sleep(20000);

//        onBackpressureLatest Example
        Flux.interval(Duration.ofMillis(10))
                .onBackpressureLatest()
                .doOnNext(i-> System.out.println("Produced: " +i))
                .delayElements(Duration.ofMillis(100))
                .subscribe(i->System.out.println("Consumed: " +i));
        Thread.sleep(5000);

//        onBackpressureError Example
        Flux.interval(Duration.ofMillis(10))
                .onBackpressureError()
                .doOnNext(i->System.out.println("Produced: " +i))
                .delayElements(Duration.ofMillis(100))
                .subscribe(i-> System.out.println("Consumed: " +i), error -> System.out.println("Error: " + error));
        Thread.sleep(5000);

//        onBackpressureDrop Example
        Flux.interval(Duration.ofMillis(10))
                .onBackpressureDrop(dropped -> System.out.println("Dropped: " + dropped))
                .doOnNext(value -> System.out.println("Produced: " + value))
                .delayElements(Duration.ofMillis(100))
                .subscribe(value -> System.out.println("Consumed: " + value));
        Thread.sleep(2000);
    }
}
