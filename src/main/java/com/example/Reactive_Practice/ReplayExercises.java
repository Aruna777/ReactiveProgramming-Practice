package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class ReplayExercises {
    public static void main(String[]args) throws InterruptedException{

//        Example 1
        Flux<Integer> flux1 =  Flux.range(1,  5).delayElements(Duration.ofMillis(200)).replay(2).autoConnect(1);;
        flux1
                .subscribe(i->System.out.println("Subscriber 1: " +i));
        Thread.sleep(3000);
        flux1
                .subscribe(
                        i->System.out.println("Subscribe 2: " +i),
                        null,
                        ()->System.out.println("Completed Flux 1"));

//        Example 2
        Flux<Integer> flux2 = Flux.range(1, 5).delayElements(Duration.ofSeconds(1)).cache();
        flux2
                .subscribe(i-> System.out.println("Subscriber 1: " +i));
        Thread.sleep(5500);
        flux2
                .subscribe(i->System.out.println("Subscriber 2: " +i),
                        null,
                        ()->System.out.println("Completed Flux 2"));
        Thread.sleep(5000);



    }
}
