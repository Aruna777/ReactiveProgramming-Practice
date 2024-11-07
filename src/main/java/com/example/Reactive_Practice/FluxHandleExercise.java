package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;

public class FluxHandleExercise {
    public static void main(String[]args){
//        Example 1
        Flux<Integer> Flux1 = Flux.range(1, 10);
        Flux1.handle(
                (number, sink)->{
                    if(number % 2==0){
                        sink.next(number * number);
                    }
        })
                .subscribe(System.out::println);

//        Example 2
        Flux<String> flux2 = Flux.just("apple", "mango", "berry", "guava",  "orange", "kiwi", "pineapple");
        flux2.handle(
                (fruits, sink)->{
                    if(fruits.length()==5){
                        sink.next(fruits.toUpperCase());
                    }
                })
                .subscribe(System.out::println);

//        Example 3
        Flux<Integer> flux3 = Flux.range(1, 50);
        flux3.handle((numbers, sink)->{
            if(numbers % 3 ==0){
                sink.next("Divisible by 3: " +numbers);
            } else if (numbers % 5 == 0) {
                sink.next("Divisible by 5: " +numbers);
            } else if (numbers % 2 == 0) {
                sink.next("Divisible by 2: " +numbers);
            } else {
                sink.next("Prime Number: " +numbers);
            }
        }).subscribe(System.out::println);
    }
}
