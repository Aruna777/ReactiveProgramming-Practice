package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelThreadExercises {
    public static void main(String[]args) throws InterruptedException{
      Flux.range(1, 10)
              .parallel()
              .runOn(Schedulers.parallel())
              .doOnNext(i-> System.out.println("Elements: " +i+" Thread name: " +Thread.currentThread().getName()))
              .subscribe();
      Thread.sleep(2000);

      Flux.range(1, 20)
              .parallel()
              .runOn(Schedulers.parallel())
              .filter(n-> n%3 == 0)
              .map(n-> n*n)
              .sequential()
              .publishOn(Schedulers.single())
              .doOnNext(i-> System.out.println("Multiples of 3: " +i+ " Thread Name: " +Thread.currentThread().getName()))
              .subscribe();


    }
}
