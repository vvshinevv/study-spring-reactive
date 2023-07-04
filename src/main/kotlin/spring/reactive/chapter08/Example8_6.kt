package spring.reactive.chapter08

import mu.KotlinLogging
import reactor.core.publisher.BufferOverflowStrategy
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

@Suppress("MagicNumber")
fun main() {
    val log = KotlinLogging.logger { }
    Flux
        .interval(Duration.ofMillis(1L))
        .doOnNext { log.info { "# emitted by original Flux: $it" } }
        .onBackpressureBuffer(
            2,
            { log.info { "** Overflow & Dropped: $it **" } },
            BufferOverflowStrategy.DROP_OLDEST
        )
        .doOnNext { log.info("[ # emitted by Buffer: $it ]") }
        .publishOn(Schedulers.parallel(), false, 1)
        .subscribe({
            Thread.sleep(1000L)
            log.info { "# onNext: $it" }
        }) { log.error { "# onError: $it" } }

    Thread.sleep(3000L)
}
