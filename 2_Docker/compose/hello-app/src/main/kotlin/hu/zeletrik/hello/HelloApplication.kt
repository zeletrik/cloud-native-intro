package hu.zeletrik.hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
class HelloApplication {
    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder) =
            restTemplateBuilder.build()
}

fun main(args: Array<String>) {
    runApplication<HelloApplication>(*args)
}
