package spring.reactive.chapter07

import mu.KotlinLogging
import reactor.core.publisher.Flux


fun main() {
    val log = KotlinLogging.logger { }

    val coldFlux = Flux
        .fromIterable(listOf("KOREA", "JAPAN", "CHINESE"))
        .map(String::lowercase)

    coldFlux.subscribe { log.info { "# subscriber1: $it" } }
    println("----------------")
    Thread.sleep(2000L)
    coldFlux.subscribe { log.info { "# subscriber2: $it" } }
}
