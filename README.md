<h1>JAVA REACTIVE PROGRAMMING</h1>

1. Mono
Mono is a publisher that emits at most one value (or no value) and then completes. It is used when you expect only a single item (or none) from the source.
Factory Methods:
• just(T data): Creates a Mono that emits a single item data and then completes.
• empty(): Creates a Mono that completes without emitting any data (empty sequence).
• error(Throwable e): Creates a Mono that terminates with the specified error.
• fromSupplier(Supplier<T>): Creates a Mono that lazily supplies a value using the provided Supplier.
• fromCallable(Callable<T>): Creates a Mono from a Callable, which emits the result of the Callable or
signals an error.
• fromRunnable(Runnable): Executes the provided Runnable and completes without emitting data.
• fromFuture(Future<T>): Converts a Future into a Mono, emitting the result when it completes.

2. Flux
Flux is a publisher that emits 0 to N elements. It's used when you expect multiple items (or none).
Factory Methods:
• just(T... values): Creates a Flux that emits the given values sequentially.
• fromArray(T[] array): Emits each element of the array one by one.
• fromIterable(Iterable<? extends T>): Converts an Iterable (like List, Set) into a Flux that emits each
element.
• fromStream(Stream<? extends T>): Emits elements from a Java Stream.
• range(int start, int count): Emits a sequence of integers starting from start and increments up to count.

3. Java Stream vs. Reactive Stream
• Java Streams: Operate on a finite set of data (collections) in a synchronous, blocking manner. Used primarily
with in-memory data. Ex: If one stream is completed and if we try to run another stream, it throws an error.
• To resolve this, we can have one publisher stream to multiple consumers. Then we can use supplier of stream.
Ex: fromStream(()-> stream())
• Reactive Streams (Mono/Flux): Can handle asynchronous, non-blocking streams of data (which may be
infinite). It can work with backpressure.

4. Log Operator
• log(): Logs each signal from the stream for debugging purposes (onNext, onError, onComplete, onSubscribe).
This can help visualize the flow of data and errors in a reactive stream.
• Acts as a middleman between producer and subscriber.

5. List and Set vs. Mono and Flux
• List/Set: Traditional in-memory data structures holding a finite number of elements that can be accessed
eagerly.
• Mono/Flux: Reactive types that deal with asynchronous data. Mono handles one item or none, whereas Flux
can handle multiple or none, emitted over time.

6. Flux
• interval(Duration duration): Emits an increasing long value at regular intervals (non-blocking).
• empty(): Creates an empty Flux that completes without emitting any data.
• defer(Supplier<Publisher<T>>): Defers the creation of the Flux until a subscriber subscribes, which can be
useful for dynamic data generation.

7. Mono/Flux Conversion
• Mono to Flux: Mono.toFlux() converts a Mono into a Flux with one item.
• Flux to Mono: Flux.next() converts a Flux into a Mono that emits only the first element.

8. Flux Sink
• Flux.create(Consumer<FluxSink<T>>): Creates a Flux and allows dynamic generation of elements. You
have control over when and how elements are emitted via the FluxSink API.

9. Thread Safety
Reactive types (Mono/Flux) are thread-safe by default, meaning multiple threads can safely subscribe or request data without causing concurrency issues. Operators like subscribeOn() and publishOn() allow controlling threading behavior in a safe manner.

10. Emit On-Demand
• FluxSink.request(long n): Allows emitting exactly n items to the subscribers on demand. Useful when
implementing backpressure strategies.
• FluxSink.OnRequest(request-{}), FluxSink.isCancelled()

11. Flux Create, EmitUntil
• Flux.create(Consumer<FluxSink<T>>): A more flexible way to create a Flux programmatically, giving
control over when and how elements are emitted. Emits multiple items.
• emitUntil(): Use conditional logic to emit values until a certain condition is met.
• Designed for single subscriber. Thread safe.
• It keeps on emitting data to sink. Will deliver everything safely to the subscriber.

12. Take, TakeWhile, TakeUntil Operators
• take(long n): Emits only the first n items and then completes.
• takeWhile(Predicate<T>): Emits items while the condition in the predicate is true.
• takeUntil(Predicate<T>): Emits items until the condition becomes true.

13. Flux Generate
• generate(Supplier< S >, BiFunction< S, FluxSink<T>, S>):
Allows generating values in a synchronous manner, maintaining internal state across emissions. Emits one value at a time.

16. Flux Handle
•Combines the logic of filter and map, allowing both synchronous emissions and conditional logic for each value.

17. Do Hooks / Callbacks
doOnComplete(): Callback when the sequence completes.
doOnRequest(): Callback when a request for n elements is made.
doOnNext(): Callback when an item is emitted.
doOnError(): Callback when an error occurs.
doOnTerminate(): Invoked when the sequence terminates, either successfully or with an error. doOnCancel(): Called when a subscription is canceled.
doOnFinally(): Executes after the sequence is completed, canceled, or errored.

18. Delay Operator
• delayElements(Duration): Delays each item emitted by the Flux by the specified duration.

19. Subscribe()
• The subscribe method triggers data flow. It has several overloaded versions allowing you to define custom behavior for onNext, onError, onComplete signals.

20. Error Handling
• onErrorReturn(T fallback): If an error occurs, returns a fallback value.
• onErrorResume(Function<Throwable, ? extends Publisher<? extends T>>): Fallback to another
Publisher when an error occurs.
• onErrorContinue(BiConsumer<Throwable, T>): Ignores the error and continues with the remaining
values.
• defaultIfEmpty(T defaultValue): Emits a default value if the sequence is empty.
• switchIfEmpty(Publisher<T>): Switches to another Publisher if the original sequence is empty.

21. Timeout
• timeout(Duration): Throws an error if the sequence doesn’t emit a value within the given duration.

22. Transform Operator
• transform(Function<Flux<T>, Publisher<R>> transformer): Transforms the current stream into another
reactive stream with a different type.

23. Cold Publisher
• A Cold Publisher starts emitting values only when a subscriber subscribes, and each subscriber receives its
own copy of the data stream.
• Independent streams. Different producer- Different subscriber
• Example: Netflix- multiple people watching the same show.

24. Hot Publisher
• A Hot Publisher starts emitting values immediately, regardless of whether there are subscribers. New
subscribers only receive events emitted after they subscribe.
• Single Producer- Multiple Subscribers. Can emit even without a subscriber.
• Example: Movie Theater, Weather App, WhatsApp Channel.

25. Auto Connect
• autoConnect(n): Converts a Cold Publisher to Hot Publisher, starting when at least n subscribers are
connected. Publish.autoconnect()

26. Replay/Cache
• replay(): Replays previous emissions to new subscribers.
• cache(): Caches and replays emitted values to new subscribers.

27. Schedulers
• Bounded Elastic: Optimized for blocking I/O tasks, expanding as needed.
• Parallel: Fixed-size thread pool designed for parallel processing tasks.
• Single: Executes tasks sequentially on a single thread.
• Immediate: Runs tasks on the current thread.

28. Operators for Scheduling
• subscribeOn(Scheduler): Specifies which thread to run the subscription logic on. For Upstream.
• publishOn(Scheduler): Specifies which thread to execute the downstream logic on. For Downstream.

29. Parallel Execution
• parallel(): Converts a Flux into a ParallelFlux for parallel processing.
• Parallel(), runOn(scheduler.parallel;)- number of CPU= number of parallel threads
• Sequential()- Use this is to bring back to sequential execution.

30. Backpressure Handling
• If the publisher keeps on publishing but the consumer could not handle it. If the queue is full, the producer stops producing.
• onBackpressureBuffer(): Buffers all emitted items when the downstream can’t keep up.
• onBackpressureDrop(): Drops the newest items if the downstream is slow.
• onBackpressureLatest(): Only the latest item is kept when the downstream is slow.
• onBackpressureError(): Signals an error when the downstream can’t handle the rate.

31. Limit Rate
• limitRate(n): Limits the number of elements requested upstream in batches, controlling backpressure.
• A way of subscriber saying how much data to produce.

32. Batching - Buffer, Error, Drop, Latest
• Batching is used when we have a flux- never ending data stream of messages. Helpful for kafka and rabbitmq.
• Similar to the backpressure strategies above, these control how excessive items are handled in different
scenarios.

33. Batching Operators
• buffer(): Collects emitted items into a list and emits that list periodically (e.g., every 5 items).
• window(): Divides the sequence into smaller Fluxes that emit a certain number of items or time windows.
• groupBy(): Groups the emitted items into multiple Fluxes based on a specified key.

34. Operators- Microservices
• startWith(): Starts the sequence with the given items.
• concatWith(): Opposite of start with, end with. Concatenates another sequence after this one completes.
• concatDelayError()- Delays the error, by checking other producer.
• merge(): Merges multiple publishers, emitting as they arrive. Merges all producers at the same time.
• zip(): Combines values from multiple publishers into a tuple. If one producer doesn’t produces data, it won’t
merge it exits.
• flatMap(): Maps each element to another Publisher and flattens the results. Sequential execution.
• ftaMapMany()- Returns many items, not one.
• concatMap(): Maps each element to another Publisher but concatenates the results sequentially.

35. CollectList, Then Operator
• collectList(): Collects all elements from the sequence into a List when it completes.
• then(): Executes when the sequence completes, useful for chaining actions. It gives the success or failure
status. It does not give the OnNext outputs., it directly gives onComplete or OnError.

36. Repeat, RepeatWhen, Retry, RetryWhen
• repeat(): Repeats the emission of values indefinitely or up to a specified number of times after the Flux
completes. i.e., resubscribes after a complete signal. If you add repeat to mono, it becomes flux, because it
continuously repeats and subscribes.
• repeatWhen(Function<Flux<Long>, Publisher<?>>): Repeats the sequence based on a trigger from
another Publisher (useful for dynamic retry logic). Can give any condition.
• retry(): Retries the sequence when an error occurs (unlimited or up to a given number of times). If no number
is mentioned, it retries for infinite times.
• retryWhen(Function<Flux<Throwable>, Publisher<?>>): Retries based on a condition or delay specified
by another Publisher.
• retryExhaustedThrow(), doBeforeRetry(), retry.max()

37. Sinks (Emit API)
• Sinks are used for manual control over emission in reactive streams. Can act like both subscriber and
publisher.
• Just want to emit items without complexity.

38.Sink API Methods:
o tryEmitValue(T value): Attempts to emit a value to subscribers.
o tryEmitNext(T value): Attempts to emit the next value.
o tryEmitEmpty(): Completes the sink without emitting any value.
o tryEmitError(Throwable t): Completes the sink with an error signal.
o emitValue(T value): Emits a value and fails if not possible (throws an exception). o emitNext(T value): Emits the next value and fails if not possible.
o emitError(Throwable t): Emits an error and fails if not possible.

39. Sink Types
• Sink.One<T>: For emitting a single value or completion.
• Sink.Many<T>: For emitting multiple values.
o Unicast: Allows only one subscriber.
o Multicast: Allows multiple subscribers but only shares values emitted after the subscription. o Replay: Replays values to late subscribers.

40. Multicast (directBestEffort, directAllOrNothing)
• directBestEffort: Emits values to subscribers in a best-effort manner, meaning some subscribers may not
receive all values. Can use if one subscriber is slow and affects the performance of the other subscriber.
• directAllOrNothing: Ensures all subscribers receive every emitted value or none at all. Can use if one
subscriber is slow, then no subscribers receives the message.

41. Sink Many Replay
• Sink.Many.replay(): Allows emitting and replaying the stream to any future subscribers, ensuring they see all
previously emitted items.

42. Context
Contexts are immutable key-value maps associated with a reactive sequence, used for passing metadata across the reactive chain. Concerns- rate limiting, authentication, and monitoring.
Context Methods:
• deferContextual(): Defer access to the current context, allowing you to retrieve it when needed.
• contextWrite(Function<Context, Context>): Mutates the current context for the downstream.
• put(K key, V value): Adds or updates a key-value pair in the context.
• putAll(Map<K,V>): Inserts all key-value pairs from a map into the context.
• delete(K key): Removes a key-value pair from the context.
• Context.of(): Creates a new context with key-value pairs.

43. Propagation
Context information is propagated between reactive components via the subscription chain. This allows the propagation of metadata like authentication tokens or correlation IDs across the system.

44. Rate Limiter with Context
• getOrEmpty(K key): Retrieves an entry from the context if it exists.
• SynchronizedMap: You can synchronize map operations with a reactive Context, ensuring thread safety in
rate-limiting scenarios.

<h1>JUNIT TESTING FOR REACTIVE STREAMS</h1>

1. StepVerifier, Verify()
• StepVerifier.create(Publisher<T>): Verifies the behavior of a reactive sequence step by step.
• verify(): Blocks until the sequence completes and verifies the result. The test gets triggered only if we use this

2. Empty/Error Validation
• expectComplete(): Asserts that the sequence completes without errors.
• expectError(Class<? extends Throwable>): Asserts that an error of the specified type is emitted.
• expectErrorMessage(String): Asserts that an error with the specified message is emitted.

3. consumeErrorWith(), consumeNextWith()
• consumeErrorWith(Consumer<Throwable>): Consumes the emitted error for further assertions.
• consumeNextWith(Consumer<T>): Consumes the next value emitted by the Flux or Mono for verification.

4. Verify/Expect
• verifyError(): Verifies that the sequence ends with an error.
• verifyComplete(): Verifies that the sequence completes successfully.
• expectError().verify(): Combines error expectation and verification.
• expectComplete().verify(): Combines completion expectation and verification.

5. Flux Testing
• thenCancel(): Cancels the subscription after performing other assertions.
• expectNext(T value): Expects the next emitted value to be equal to value.
• expectNextCount(long count): Expects count number of values to be emitted.
• expectNextMatches(Predicate<T>): Expects the next value to match the given condition.

6. Condition
• thenConsumeWhile(Predicate<T>): Asserts that elements match a condition while consuming them.

7. assertNext(), collectAll()
• assertNext(Consumer<T>): Asserts that the next value meets some condition.
• collectAll(): Collects all emitted values for further validation.

8. Virtual Time Scheduler
• thenAwait(Duration duration): Advances time by the given duration for testing time-dependent sequences.

9. Scenario Name, Step Description
• Use .as(String description) to name a scenario in StepVerifier for easier identification in test results.
• senarioName()

10. Context Testing
• create().withInitialContext(Context.of()): Initializes the StepVerifier with a preconfigured context.

11. Test Publisher
• Publisher.complete(): Signals completion to the test publisher.
• Publisher.emit(T... values): Emits the given values in a test scenario.

12. Timeout Test
• verify(Duration.ofMillis(long)): Asserts that the sequence completes within the specified time.
• We can use this if we want out test to complete in specific time.

13. Assertions
Assertions in Reactive Streams Testing help verify emitted values, errors, and timing. Examples include:
• assertNext()
• assertError()
• assertComplete()
• assertTimeout().
