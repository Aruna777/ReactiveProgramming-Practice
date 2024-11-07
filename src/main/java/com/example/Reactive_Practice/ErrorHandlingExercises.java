package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;

public class ErrorHandlingExercises {
    public static void main(String[]args){

//        onErrorReturn Example
        Flux<Integer> flux1 = Flux.range(1, 5);
        flux1
                .map(i->{
                    if(i>3) throw new RuntimeException("Error occurred");
                    return i;
                })
                .onErrorReturn(10)
                .subscribe(
                       System.out::println,
                       Throwable::printStackTrace,
                       ()-> System.out.println("Completed flux 1"));

//        onErrorResume Example
        Flux<String> flux2 = Flux.just("A", "B", "C");
        flux2
                .map(i-> {
                    if(i.equals("C")) throw new RuntimeException("Error occurred");
                    return i;
                })
                .onErrorResume(throwable -> Flux.just("X", "Y", "Z"))
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        ()-> System.out.println("Completed flux 2"));

//        onErrorContinue Example
        Flux<Integer> flux3 = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9);
        flux3
                .map(i-> {
                    if(i.equals(5)) throw new RuntimeException("Error occurred");
                    return i;
                })
                .onErrorContinue((throwable, o) -> System.out.println("Skipping item") )
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        ()-> System.out.println("Completed flux 3"));

//        defaultIfEmpty Example
        Flux<Integer> flux4 = Flux.empty();
        flux4
                .defaultIfEmpty(100)
                .subscribe(
                System.out::println,
                Throwable::printStackTrace,
                ()-> System.out.println("Completed flux 4"));

//        SwitchIfEmpty Example
        Flux<String> flux5 = Flux.empty();
        flux5
                .switchIfEmpty(Flux.just("No results found"))
                .subscribe(
                System.out::println,
                Throwable::printStackTrace,
                ()-> System.out.println("Completed flux 5")
                );
    }
}
