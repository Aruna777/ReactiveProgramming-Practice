package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.Random;
import java.util.Scanner;

public class FluxExercises {
public static final String[] words = {"Hello","Welcome", "Hola", "Ciao"};

    public static void main(String[]args) throws InterruptedException {
//        Example 1
        Flux<Integer> flux1 = Flux.create(sink-> {
           for(int i= 0; i<=10; i++){
               sink.next(i);
               try{
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   sink.error(e);
                   return;
               }
           }sink.complete();
        },FluxSink.OverflowStrategy.BUFFER);
        flux1 .subscribeOn(Schedulers.parallel())
                .subscribe(
                        numbers-> System.out.println("Received: " +numbers),
                        error-> System.out.println("Error: "+ error),
                        ()-> System.out.println("Completed")

        );Thread.sleep(6000);

//            Example 2
            Flux<String> randomString = Flux.create(sink-> {
                Random random = new Random();
                for(int i = 0; i<=4; i++ ){
                    sink.next(words[random.nextInt(words.length)]);
                }
                sink.complete();
            });
            randomString.subscribeOn(Schedulers.parallel()).subscribe(
                    string-> System.out.println("Received: " +string ),
                    error-> System.out.println("Error: " +error),
                    ()-> System.out.println("Completed")

            );Thread.sleep(6000);

//        Example 3
        Flux<String> eventFlux = Flux.create(sink->{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type 'Start' to start emitting and 'Stop' to cancel emitting");
            while (true ) {
                String input = scanner.nextLine();
                if("Stop".equalsIgnoreCase(input)){
                    sink.complete();
                    break;
                } else if ("Start".equalsIgnoreCase(input)) {
                    sink.next("Event generated on Start command");
                } else{
                    sink.error(new RuntimeException("Unknown"));
                    break;
                }

            } scanner.close();
        });
        eventFlux
                .subscribe(
                        string ->   System.out.println("Emitting: " +string),
                        error-> System.out.println("Error:" +error),
                        ()-> System.out.println("Completed")
                );Thread.sleep(10000);

//        Example 4
        Flux<String> fluxThread = Flux.just("Apple", "Apricot", "Amla", "Banana","Mango", "Cherry", "Anona");
        fluxThread.doOnNext( n-> System.out.println("Current Thread name: " +Thread.currentThread().getName()))
                .subscribeOn(Schedulers.parallel())
                .filter(n-> n.startsWith("A"))
                .publishOn(Schedulers.single())
                .map(String::toUpperCase)
                .doOnNext(n-> System.out.println("Processing " +n +" on thread " +Thread.currentThread().getName()))
                .subscribe(n-> System.out.println("Received " +n + " on thread " +Thread.currentThread().getName()));
        Thread.sleep(10000);

   }

}
