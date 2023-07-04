package spring.reactive.chapter08

import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

@Suppress("MagicNumber")
fun main() {
    val log = KotlinLogging.logger { }
    Flux
        .interval(Duration.ofMillis(1L))
        .onBackpressureBuffer()
        .doOnNext { data -> log.info { "# doOnNext: $data" } }
        .publishOn(Schedulers.parallel())
        .subscribe({ data ->
            Thread.sleep(5L)
            log.info { "# onNext: $data" }
        }) {
            log.error { "# onError" }
        }

    Thread.sleep(3000L)
}
