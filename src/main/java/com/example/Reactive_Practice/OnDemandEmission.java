package com.example.Reactive_Practice;

import reactor.core.publisher.Flux;


public class OnDemandEmission {
    public static void main(String[]args) {
        Flux.create(sink -> {
                    int[] count = {1};
                    sink.onRequest(n -> {
                        for (int i = 0; i < n; i++) {
                            sink.next("Item: " + count[0]++);
                        }
                        if (count[0] > n) {
                            sink.complete();
                        }
                    });
        })
                .doOnTerminate(() -> System.out.println("stream terminate"))
                .subscribe(
                        n -> System.out.println("Received " + n),
                        error -> System.out.println("Error " + error),
                        () -> System.out.println("Completed"),
                        subscription -> subscription.request(15)

                );
    }
}
