package chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_9 {
    public static void main(String[] args) {
        Flux.generate(() -> 0, (state, sink) -> {
                    sink.next(state);
                    if (state == 10) {
                        sink.complete();
                    }
                    return ++state;
                })
                .subscribe(data -> log.info("# onNext: {}", data));
    }
}
