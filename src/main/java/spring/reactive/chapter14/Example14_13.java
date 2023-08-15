package spring.reactive.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;
import spring.reactive.chapter14.model.CryptoCurrencyPriceEmitter;
import spring.reactive.chapter14.model.CryptoCurrencyPriceListener;

import java.util.List;

@Slf4j
public class Example14_13 {
    public static void main(String[] args) throws InterruptedException {
        CryptoCurrencyPriceEmitter priceEmitter = new CryptoCurrencyPriceEmitter();

        Flux.create((FluxSink<Integer> sink) -> priceEmitter.setListener(new CryptoCurrencyPriceListener() {
                    @Override
                    public void onPrice(List<Integer> priceList) {
                        priceList.forEach(sink::next);
                    }

                    @Override
                    public void onComplete() {
                        sink.complete();
                    }
                }))
                .publishOn(Schedulers.parallel())
                .subscribe(
                        data -> log.info("# onNext: {}", data), error -> {
                        }, () -> log.info("# onComplete")
                );

        Thread.sleep(3000L);
        priceEmitter.flowInto();

        Thread.sleep(2000L);
        priceEmitter.complete();
    }
}
