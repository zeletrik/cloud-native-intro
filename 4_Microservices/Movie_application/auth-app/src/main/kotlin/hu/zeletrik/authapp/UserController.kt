package hu.zeletrik.authapp

import hu.zeletrik.authapp.data.UserRepository
import hu.zeletrik.authapp.data.entity.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class UserController(@Autowired val userRepository: UserRepository) {

    companion object {
        private const val UserById = "/find/{id}"
        private const val AllUser = "/find/all"
        private const val AddUser = "/add"
    }

    @GetMapping(UserById)
    fun getUser(@PathVariable id: String) =
            userRepository.findById(id)
                    .map { ResponseEntity.ok(it) }
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build())

    @GetMapping(AllUser)
    fun getAllUser() = userRepository.findAll()

    @PostMapping(AddUser)
    fun addUser(@RequestBody userEntity: UserEntity) = userRepository.save(userEntity)

}
