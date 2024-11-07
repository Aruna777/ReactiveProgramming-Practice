package com.example.Reactive_Practice;


import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotPublisherExample {
    public static void main(String[]args) throws InterruptedException{

//          Example 1
        Flux<String> temperatureFlux = Flux.interval(Duration.ofSeconds(1))
                .map(i -> "Temperature Reading: " + (20 + i) + "°C")
                .share();

        Thread.sleep(1000);
        temperatureFlux.subscribe(data -> System.out.println("Subscriber 1: " + data));
        temperatureFlux.subscribe(data -> System.out.println("Subscriber 2: " + data));
        Thread.sleep(3000);
        temperatureFlux.subscribe(data -> System.out.println("Subscriber 3: " + data));
        Thread.sleep(5000);

//        Example 2
        Flux<String> flux2 = Flux.interval(Duration.ofSeconds(1))
                .map(i-> "Scores: " + (10*i) + " Yay")
                .share();

        flux2.subscribe(i-> System.out.println("Subscriber 1: " +i));
        Thread.sleep(3000);
        flux2.subscribe(i-> System.out.println("Subscriber 2: " +i));
        Thread.sleep(3000);
        flux2.subscribe(i-> System.out.println("Subscriber 3: " +i));
        Thread.sleep(5000);


    }
}

