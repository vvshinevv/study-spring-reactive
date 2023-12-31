package spring.reactive.chapter05

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun main() {
    val sequence = Flux.just("hello", "reactor")
    sequence.map { it.uppercase() }.subscribe { println(it) }
}
