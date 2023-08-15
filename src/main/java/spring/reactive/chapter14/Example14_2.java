package spring.reactive.chapter14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class Example14_2 {
    public static void main(String[] args) {
        Flux.fromIterable(List.of(1, 2, 3, 4, 5)).subscribe(coin -> log.info("number :: {}", coin));
    }
}
