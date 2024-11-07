package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;

import java.util.Random;

public class EmitUntilExercises {

    public static void main(String[]args){
        Random random = new Random();
        Flux<Integer> emitUntil = Flux.create(emitter-> {
            while(true){
                int value = random.nextInt(100)+1;
                emitter.next(value);

                if(value>90){
                    emitter.complete();
                    break;
                }
            }

        });
        emitUntil.subscribe(
                item-> System.out.println("Emitted: " +item),
                error-> System.out.println("Error: " +error),
                ()-> System.out.println("Completed")

        );
    }
}
