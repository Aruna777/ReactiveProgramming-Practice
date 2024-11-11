package com.example.Reactive_Practice;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.time.Duration;

public class MonoFluxExercisesTest {
    MonoFluxExercises monoFluxExercises = new MonoFluxExercises();

    @Test
    void getHelloWorldMonoTest(){
        StepVerifier.create(monoFluxExercises.getHelloWorldMono())
                .expectNext("Hello World")
                .verifyComplete();
    }

    @Test
    void getEmptyMonoTest(){
        StepVerifier.create(monoFluxExercises.getEmptyMono())
                .expectNext()
                .verifyComplete();
    }

    @Test
    void getErrorMonoTest() {
        StepVerifier.create(monoFluxExercises.getErrorMono())
                .expectNext(3)
                .verifyComplete();
    }

    @Test
    void getMappedMonoTest(){
        StepVerifier.create(monoFluxExercises.getMappedMono())
                .expectNext(20)
                .verifyComplete();
    }
    @Test
    void getDelayedMonoTest(){
        StepVerifier.withVirtualTime(()-> monoFluxExercises.getDelayedMono())
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("Hello and Welcome ")
                .verifyComplete();
    }

    @Test
    void getFlatMappedMonoTest(){
        StepVerifier.create(monoFluxExercises.getFlatMappedMono())
                .expectNext("user for User123")
                .verifyComplete();
    }

    @Test
    void getRangeFluxTest(){
        StepVerifier.create(monoFluxExercises.getRangeFlux())
                .expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .verifyComplete();
    }

    @Test
    void getMixedFluxTest(){
        StepVerifier.create(monoFluxExercises.getMixedFlux())
                .expectNext("String", 123, true)
                .verifyComplete();
    }

    @Test
    void getFilteredFluxTest(){
        StepVerifier.create(monoFluxExercises.getFilteredFlux())
                .expectNext(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)
                .verifyComplete();
    }

    @Test
    void getMappedFluxTest(){
        StepVerifier.create(monoFluxExercises.getMappedFlux())
                .expectNext("JOHN", "JANE", "TOM")
                .verifyComplete();
    }

    @Test
    void getErrorHandledFluxTest(){
        StepVerifier.create(monoFluxExercises.getErrorHandledFlux())
                .expectNext(1, 2, 3, 99)
                .verifyComplete();
    }

    @Test
    void getMergedFluxTest(){
        StepVerifier.create(monoFluxExercises.getMergedFlux())
                .expectNext(1, 2, 3, 4, 5, 6, 7, 8 ,9, 10)
                .verifyComplete();
    }

    @Test
    void getCombinedNamesAndHobbiesTest(){
        StepVerifier.create(monoFluxExercises.getCombinedNamesAndHobbies())
                .expectNext("Practice art",  "Practice dance", "Practice sing", "Practice play")
                .verifyComplete();
    }

    @Test
    void getLimitedRateInfiniteFluxTest() {
        StepVerifier.create(MonoFluxExercises.getLimitedRateInfiniteFlux().take(5))
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void getSquaredFluxTest(){
        StepVerifier.create(MonoFluxExercises.getSquaredFlux())
                .expectNext(1, 4, 9, 16, 25)
                .verifyComplete();
    }

    @Test
    void getZippedNamesFluxTest(){
        StepVerifier.create(monoFluxExercises.getZippedNamesFlux())
                .expectNext("alice & lily", "bob & kristy", "kate & Miha")
                .verifyComplete();
    }

    @Test
    void getLoggedFluxTest(){
        StepVerifier.create(monoFluxExercises.getLoggedFlux())
                .expectNext("apple", "banana", "mango", "berry" )
                .verifyComplete();
    }

    @Test
    void getRetryFluxTest(){
        StepVerifier.create(monoFluxExercises.getRetryFlux())
                .expectNext(1, 2, 3, 1, 2, 3 ,1, 2, 3, 99)
                .verifyComplete();
    }
}
