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
        .onBackpressureLatest()
        .publishOn(Schedulers.parallel())
        .subscribe({
            Thread.sleep(5L)
            log.info { "# onNext: $it" }
        }) { log.error { "# onError: $it" } }

    Thread.sleep(3000L)
}
