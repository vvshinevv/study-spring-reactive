package spring.reactive

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringReactiveApplication

fun main(args: Array<String>) {
	runApplication<SpringReactiveApplication>(*args)

	object {
		val log = KotlinLogging.logger { }
	}
}
