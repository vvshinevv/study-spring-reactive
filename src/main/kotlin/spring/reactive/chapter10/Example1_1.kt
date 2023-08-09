package spring.reactive.chapter10

import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.publisher.Hooks
import reactor.core.scheduler.Schedulers

fun main() {
    val log = KotlinLogging.logger { }

    Flux
        .fromArray(arrayOf(1, 3, 5, 7))
        .doOnNext { data -> log.info("# doOnNext fromArray: {}", data) }
        .subscribeOn(Schedulers.newSingle("aaaa"))
        .log()
        .subscribeOn(Schedulers.parallel())
        .subscribeOn(Schedulers.single())
        .subscribe()



}
