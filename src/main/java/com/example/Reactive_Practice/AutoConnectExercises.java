package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;

public class AutoConnectExercises {
    public static void main(String[]args)throws InterruptedException{

//        Example 1
        Flux<Integer> flux1 = Flux.range(1, 5).publish().autoConnect(2);
        flux1
                .subscribe(i-> System.out.println("Subscriber 1: " +i));
        flux1
                .map(i-> i*2)
                .subscribe(
                        i-> System.out.println("Subscriber 2:" +i),
                        null,
                        ()-> System.out.println("Completed Flux 1"));

//        Example 2
        Flux<Integer> flux2 = Flux.range(1, 10).publish().autoConnect(2);
        flux2
                .subscribe(i-> System.out.println("Subscriber 1: " +i));
        Thread.sleep(3000);
        flux2
                .subscribe(
                i->System.out.println("Subscriber 2: "+ i),
                null,
                ()-> System.out.println("Completed Flux 2"));
        Thread.sleep(5000);

    }
}
