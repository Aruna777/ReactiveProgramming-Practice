package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class MulticastExercises {
    public static void main(String[]args){

//        DirectBestEffort Example
        Sinks.Many<String> sink1 = Sinks.many().multicast().directBestEffort();
        Flux<String> flux1 = sink1
                .asFlux()
                .doOnNext( i-> System.out.println("Received From 1: " +i));
        Flux<String> flux2 = sink1
                .asFlux()
                .delayElements(Duration.ofMillis(100))
                .doOnNext(i-> System.out.println("Received From 2: " +i));

        flux1.subscribe();
        flux2.subscribe();
        System.out.println("Emitted Value: " +sink1.tryEmitNext("A"));
        System.out.println("Emitted Value: " +sink1.tryEmitNext("B"));
        System.out.println("Emitted Value: " +sink1.tryEmitNext("C"));

//        DirectAllOrNothing Example
        Sinks.Many<Integer> sink2 = Sinks.many().multicast().directAllOrNothing();
        Flux<Integer> flux3 = sink2
                .asFlux()
                .doOnNext(i->System.out.println("Received 1: " +i));
        Flux<Integer> flux4 = sink2
                .asFlux()
                .delayElements(Duration.ofMillis(100))
                .doOnNext(i-> System.out.println("Received 2: " +i));

        flux3.subscribe();
        flux4.subscribe();
        System.out.println("Emitted values: " +sink2.tryEmitNext(1));
        System.out.println("Emitted values: " +sink2.tryEmitNext(2));
        System.out.println("Emitted values: " +sink2.tryEmitNext(3));
    }
}
