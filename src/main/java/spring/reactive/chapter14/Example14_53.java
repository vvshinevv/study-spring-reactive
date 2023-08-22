package spring.reactive.chapter14;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class Example14_53 {
    public static void main(String[] args) {
        Flux.range(1, 11)
                .window(3)
                .flatMap(flux -> {
                    log.info("========");
                    return flux;
                })
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(@NotNull Subscription subscription) {
                        subscription.request(2);
                    }

                    @Override
                    protected void hookOnNext(@NotNull Integer value) {
                        log.info("# onNext: {}", value);
                        try {
                            Thread.sleep(2000L);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        request(2);
                    }
                });
    }
}
