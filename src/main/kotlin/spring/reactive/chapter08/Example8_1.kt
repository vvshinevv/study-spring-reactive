package spring.reactive.chapter08

import mu.KotlinLogging
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

@Suppress("MagicNumber")
fun main() {
    val log = KotlinLogging.logger { }
    Flux.range(1, 5)
        .doOnRequest { data -> log.info { "# doOnRequest: $data" } }
        .subscribe {
            object : BaseSubscriber<Int>() {
                override fun hookOnSubscribe(subscription: Subscription) {
                    request(1)
                }

                override fun hookOnNext(value: Int) {
                    Thread.sleep(2000L)
                    log.info { "# hookOnNext: $value" }
                    request(1)
                }
            }
        }
}
