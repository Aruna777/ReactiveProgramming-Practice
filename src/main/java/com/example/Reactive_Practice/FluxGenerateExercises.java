package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FluxGenerateExercises {
    public static void main(String[]args){
//        Example 1
        Flux<Integer> flux1 = Flux.generate(
                () -> 0,
                (count, sink) -> {
                    if (count >= 10) {
                        sink.complete();
                    } else {
                        int random = ThreadLocalRandom.current().nextInt(1, 99);
                        sink.next(random);
                    }
                    return count + 1;
                }
        );
        flux1.subscribe(System.out::println);

//        Example 2

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana", "Eve");
        Flux<String> flux2 = Flux.generate(
                ()-> 0,
                (index, sink)-> {
                    if(index>=names.size()){
                        sink.complete();
                    }
                    else{
                        sink.next(names.get(index));
                    }
                    return index+1;
                });
        flux2.subscribe(System.out::println);
    }
}
