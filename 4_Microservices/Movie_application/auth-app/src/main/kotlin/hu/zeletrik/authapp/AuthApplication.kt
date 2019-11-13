package hu.zeletrik.authapp

import io.jaegertracing.Configuration
import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
class AutApplication {

	@Bean
	fun jaegerTracer() =
			Configuration.fromEnv().tracer

	@Bean
	fun restTemplate(restTemplateBuilder: RestTemplateBuilder)=
			restTemplateBuilder.build()

	@Bean
	fun timedAspect(registry: MeterRegistry) =
			TimedAspect(registry)
}

fun main(args: Array<String>) {
	runApplication<AutApplication>(*args)
}
