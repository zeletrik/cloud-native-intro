package hu.zeletrik.chain

import io.jaegertracing.Configuration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry

@SpringBootApplication
class ChainApplication {

	@Bean
	fun jaegerTracer() =
			Configuration.fromEnv().tracer

	@Bean
	fun restTemplate(restTemplateBuilder: RestTemplateBuilder) =
			restTemplateBuilder.build()

	@Bean
	fun timedAspect(registry: MeterRegistry) =
			TimedAspect(registry)
}

fun main(args: Array<String>) {
	runApplication<ChainApplication>(*args)
}
