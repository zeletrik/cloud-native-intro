package hu.zeletrik.kubernetes.hello

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class HelloResponse(val message: String)

@RestController
@RequestMapping("/hello")
class HelloController {

    companion object {
        private const val Hello = "/{user}"
        private const val Ping = "/ping"
    }

    @GetMapping(Ping)
    fun ping() =
            "Pong"

    @GetMapping(Hello)
    fun hello(@PathVariable user: String) =
            ResponseEntity.ok().body(HelloResponse("Hello $user!"))
}
