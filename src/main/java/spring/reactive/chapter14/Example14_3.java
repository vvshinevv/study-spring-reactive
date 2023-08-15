package spring.reactive.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.stream.Stream;

@Slf4j
public class Example14_3 {
    public static void main(String[] args) {
        Flux.fromStream(() -> Stream.of("BTC", "DOGE"))
                .filter(coin -> coin.equals("BTC") || coin.equals("ETH"))
                .subscribe(data -> log.info("{}", data));
    }
}
