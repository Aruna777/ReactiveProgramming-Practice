package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class SchedulersExercises {
    public static void main(String[]args)throws InterruptedException{

//        Parallel Example
        Flux.range(1, 10)
                .publishOn(Schedulers.parallel())
                .map(n->n*2)
                .subscribe(i-> System.out.println("Number: " + i + " ThreadName: " +Thread.currentThread().getName()));
        Thread.sleep(1000);

//        Single Example
        Mono.just("Hello All")
                .map(String::toUpperCase)
                .subscribeOn(Schedulers.single())
                .subscribe(i->System.out.println("Message: " + i + " Current Thread: " +Thread.currentThread().getName()));
        Thread.sleep(3000);

//        Immediate Example
        Flux.range(1, 10)
                .publishOn(Schedulers.immediate())
                .filter(n-> n%3 ==0)
                .subscribe(i-> System.out.println("Odd Numbers: " +i+ " Thread processed: " +Thread.currentThread().getName()));
        Thread.sleep(3000);

//        Combining Schedulers
        Flux.just("apple", "mango", "orange", "papaya", "kiwi", "banana")
                .publishOn(Schedulers.parallel())
                .filter(i-> i.length()>5)
                .doOnNext(i->System.out.println("Fruit Name: " +i+ " Processed on Thread: " +Thread.currentThread().getName()))
                .publishOn(Schedulers.single())
                .map(String::toUpperCase)
                .doOnNext(i->System.out.println("Fruit Name: " +i+ " Processed on Thread: " +Thread.currentThread().getName()))
                .subscribe();
        Thread.sleep(3000);

    }
}
