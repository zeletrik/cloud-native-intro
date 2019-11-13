package hu.zeletrik.hello

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

data class HelloResponse(val message: String)

@RestController
class HelloController(@Autowired val restTemplate: RestTemplate) {

    companion object {
        private const val Hello = "/random"
        private const val Ping = "/ping"
    }

    @GetMapping(Ping)
    fun ping() =
            "Pong"

    @GetMapping(Hello)
    fun hello(): ResponseEntity<Any> {
        val response = restTemplate.getForEntity("http://name:8080/name/random", String::class.java).body
        return ResponseEntity.ok().body(HelloResponse("Hello $response!"))
    }
}
