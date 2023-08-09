package spring.reactive.chapter09

import mu.KotlinLogging
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.core.scheduler.Schedulers
import java.util.stream.IntStream

@Suppress("MagicNumber")
fun main() {
    val log = KotlinLogging.logger { }
    val tasks = 6
    Flux
        .create { sink -> IntStream.range(1, tasks).forEach { sink.next(doTasks(it)) } }
        .subscribeOn(Schedulers.boundedElastic())
        .doOnNext { log.info { "# create(): $it" } }
        .publishOn(Schedulers.parallel())
        .map { "success $it" }
        .doOnNext { log.info { "# map(): $it" } }
        .publishOn(Schedulers.parallel())
        .subscribe { log.info { "# onNext: $it" } }

    val a: Sinks.Many<Int> = Sinks.many().unicast().onBackpressureBuffer()
    a.asFlux()
}

fun doTasks(taskNumber: Int): String {
    return "task $taskNumber result"
}
