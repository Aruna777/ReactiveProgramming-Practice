package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;

public class CollectListThenExercises {
    public static void main(String[]args){

//        CollectList Example
        Flux<String> flux1 = Flux.just("Alice", "Bob", "Charlie", "David");
        flux1
                .collectList()
                .subscribe(System.out::println);

//        CollectList and Then Example 1
        Flux<Integer> flux2 = Flux.just(1, 2, 3, 4, 5);
        flux2
                .collectList()
                .then().doOnTerminate(() -> System.out.println("Sequence Completed"))
                .subscribe();

//        Then Example
        Flux<String> flux3 = Flux.just("Alpha", "Beta")
                .concatWith(Flux.error(new RuntimeException("Error occurred: ")))
                 .concatWith(Flux.just("Gamma"));
        flux3
                .then()
                .doOnSuccess(i->System.out.println("Completed"))
                .doOnError(e-> System.out.println("Error: "+ e.getMessage()))
                .subscribe();

//        CollectList and Then Example 2
        Flux<String> flux4 = Flux.just("http://example.com/1", "http://example.com/2", "http://example.com/3");
        flux4
                .collectList()
                .then()
                .doOnTerminate(()->System.out.println("All URLs fetched"))
                .subscribe();

    }
}
