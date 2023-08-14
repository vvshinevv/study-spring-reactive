package spring.reactive.chapter14

import mu.KotlinLogging
import reactor.core.publisher.Mono

fun main() {
    val logger = KotlinLogging.logger {}
    Mono.just { null }
        .subscribe(
            { _ -> },
            { _ -> },
            { logger.info { "# onCompleted" } }
        )
}
