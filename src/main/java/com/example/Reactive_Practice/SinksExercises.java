package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinksExercises {
    public static void main(String[]args){

//        Sinks Many with TryEmitNext and TryEmitComplete
        Sinks.Many<String> sink1 = Sinks.many().multicast().onBackpressureBuffer();
        Flux<String> flux1 = sink1.asFlux();
        flux1.subscribe(
                i-> System.out.println("Received: " +i),
                e-> System.out.println("Error: " +e.getMessage()),
                ()-> System.out.println("Completed")
        );
        var result1 = sink1.tryEmitNext("Hello");
        System.out.println("Value Emitted: " +result1);

        var result2 = sink1.tryEmitNext("Welcome");
        System.out.println("Value Emitted: " +result2);

        sink1.tryEmitComplete();

//        Sinks Many with TryEmitNext and TryEmitComplete
        Sinks.Many<Integer> sinks2 = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> flux2 = sinks2.asFlux();
        flux2.subscribe(
                i-> System.out.println("Received: " +i),
                e-> System.out.println("Error: " ),
                ()-> System.out.println("Completed"));
        System.out.println("Emitted value: " +sinks2.tryEmitNext(1));
        System.out.println("Emitted Value: " +sinks2.tryEmitNext(2));
        System.out.println("Emitted Value: "+sinks2.tryEmitNext(3));
        sinks2.tryEmitComplete();

//
        Sinks.One<Integer> sinks3 = Sinks.one();
        Mono<Integer> mono1 = sinks3.asMono();
        mono1
                .subscribe(
                        i->System.out.println("Received: " +i),
                        e-> System.out.println("Error: "),
                        ()->System.out.println("Completed")
                );
        System.out.println("Emitted Value: " +sinks3.tryEmitValue(1));

//        TryEmitEmpty Example
        Sinks.One<Integer> sinks4 = Sinks.one();
        Mono<Integer> mono2 = sinks4.asMono();
        mono2
                .subscribe(
                        i->System.out.println("Received: " +i),
                        e-> System.out.println("Error: "),
                        ()->System.out.println("Completed")
                );
        var result = sinks4.tryEmitEmpty();
        System.out.println("Emitted Value: " +result);
    }
}
