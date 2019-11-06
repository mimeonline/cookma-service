package io.cookma

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CookmaApplication

fun main(args: Array<String>) {
	runApplication<CookmaApplication>(*args)
}
