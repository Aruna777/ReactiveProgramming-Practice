package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinksReplayExercises {
    public static void main(String[]args) throws InterruptedException{

//        Replay all Example
        Sinks.Many<Integer> sinks1 = Sinks.many().replay().all();

        sinks1.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        sinks1.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
        sinks1.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
        sinks1.emitNext(4, Sinks.EmitFailureHandler.FAIL_FAST);
        sinks1.emitNext(5, Sinks.EmitFailureHandler.FAIL_FAST);

        Flux<Integer> flux1 = sinks1.asFlux();
        flux1.subscribe(i-> System.out.println("Received 1: " +i));
        Thread.sleep(1000);

        Flux<Integer> flux2 = sinks1.asFlux();
        flux2.subscribe(i-> System.out.println("Received 2: " +i));

//        Replay Limited History Example
        Sinks.Many<String> sinks2 = Sinks.many().replay().limit(3);

        sinks2.emitNext("A", Sinks.EmitFailureHandler.FAIL_FAST);
        sinks2.emitNext("B", Sinks.EmitFailureHandler.FAIL_FAST);
        sinks2.emitNext("C", Sinks.EmitFailureHandler.FAIL_FAST);
        sinks2.emitNext("D", Sinks.EmitFailureHandler.FAIL_FAST);
        sinks2.emitNext("E", Sinks.EmitFailureHandler.FAIL_FAST);

        Flux<String> flux3 = sinks2.asFlux();
        flux3.subscribe(i->System.out.println("Received: 1 "+i));
        Thread.sleep(1000);
        Flux<String> flux4 = sinks2.asFlux();
        flux4.subscribe(i->System.out.println("Received: 2 "+i));

    }
}
