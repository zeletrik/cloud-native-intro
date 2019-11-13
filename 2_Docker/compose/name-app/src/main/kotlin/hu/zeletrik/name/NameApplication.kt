package hu.zeletrik.name

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NameApplication

fun main(args: Array<String>) {
    runApplication<NameApplication>(*args)
}
