package spring.reactive.springreactive.chapter05

import reactor.core.publisher.Flux

fun main() {
    val sequence = Flux.just("hello", "reactor")
    sequence.map { it.uppercase() }.subscribe { println(it) }
}
