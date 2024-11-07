package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class DoHooksExercises {
    public static void main(String[]rgs) throws InterruptedException{

//        doOnNext Example
        Flux<Integer> flux1 = Flux
                .range(1, 10)
                .doOnNext(n-> System.out.println("Received: " +n));
        flux1.subscribe(System.out::println);

//        doOnComplete Example
        Flux<String> flux2 = Flux.just("Hello", "welcome", "How are you doing today")
                .doOnNext(n-> System.out.println("Received: "+n))
                .delayElements(Duration.ofMillis(500))
                .doOnComplete(() -> System.out.println("Flux completed successfully"));
        flux2.subscribe();
        Thread.sleep(2000);

//        doOnRequest Example
        Flux<Integer> flux3 = Flux.range(1, 10);
        flux3.doOnRequest(n-> System.out.println("Requesting: " + 5 + " items"))
                .subscribe();

//        doOnError Example
        Mono<Integer> mono1 = Mono.error(new RuntimeException("Something went wrong"));
        mono1.doOnError(e-> System.out.println("Error occurred: " +e.getMessage()))
                .subscribe(
                        value -> System.out.println("Received: " + value),
                        error -> System.out.println("Error in subscribe: " + error.getMessage()));

//        doOnTerminate Example
        Mono<String> mono2 = Mono.just("Hello world")
                .delayElement(Duration.ofSeconds(3))
                .doOnTerminate(()->System.out.println("Mono Terminated"));
        mono2.subscribe();
        Thread.sleep(3000);

//        doOnCancel Example
        Flux<Integer> slowFlux = Flux.range(1, 10)
                .delayElements(Duration.ofMillis(500))
                .doOnCancel(() -> System.out.println("Subscription was canceled"));
        slowFlux.subscribe(System.out::println);
        Thread.sleep(2000);

//        doOnFinally Example
        Mono<Integer> mono3 = Mono.error(new RuntimeException("Error occurred: "));
        mono3.doFinally(e-> System.out.println("Mono Finally" +e)).subscribe();
        Thread.sleep(2000);
    }

}
