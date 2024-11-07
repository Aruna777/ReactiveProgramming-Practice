package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;

public class TransformOperatorExercises {
    public static void main(String[]args){

//        Example 1
        Flux<Integer> flux1 = Flux.range(1, 5);
        flux1
                .transform(flux-> Flux.just("Hello"))
                .subscribe(
                        System.out::println,
                        null,
                        ()-> System.out.println("Completed Flux 1"));

//        Example 2
        Flux<Integer> flux2 = Flux.range(1, 10);
        flux2
                .transform(flux-> flux
                .filter(n->n %2 ==0)
                .map(n->n *2 ))
                .subscribe(
                        System.out::println,
                        null,
                        ()-> System.out.println("Completed Flux 2"));

//        Example 3
        Flux<String> flux3 = Flux.just("apple", "banana", "cherry", "date");
        flux3.transform(flux->flux
                .map(String::length)
                .filter(length-> length >5))
                .subscribe(
                        System.out::println,
                        null,
                        ()-> System.out.println("Completed Flux 3"));

    }
}
