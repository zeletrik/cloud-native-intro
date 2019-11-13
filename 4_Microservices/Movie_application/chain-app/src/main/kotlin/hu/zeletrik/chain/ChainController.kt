package hu.zeletrik.chain

import hu.zeletrik.chain.data.entity.FavoriteEntity
import hu.zeletrik.chain.data.entity.MovieEntity
import hu.zeletrik.chain.data.entity.UserEntity
import io.micrometer.core.annotation.Timed
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.*
import kotlin.collections.ArrayList

@RestController
@RequestMapping("/chain")
class ChainController(
        val restTemplate: RestTemplate) {
    private val LOGGER = LoggerFactory.getLogger(ChainController::class.java)

    companion object {
        private const val Hello = "/hello"
        private const val FavoriteMovies = "/favorite"
    }

    @GetMapping(Hello)
    fun hello() = "Hello world!"

    @GetMapping(FavoriteMovies)
    @Timed(value = "get_all_favorite_for_random_user")
    fun favorite(): ResponseEntity<Response> {
        LOGGER.info("Get favorites for random user")

        val userList = restTemplate.getForEntity("http://auth-service:3002/user/find/all", Array<UserEntity>::class.java).body!!.asList()
        val randomId = userList.shuffled().take(1)[0].userId


        val favoriteEntity = restTemplate.getForEntity("http://favorite-service:3003/favorite/find/$randomId", FavoriteEntity::class.java).body!!
        val favorites: MutableList<MovieEntity> = ArrayList()

        favoriteEntity.favorites.forEach {
            val resp = restTemplate.getForEntity("http://movie-service:3000/movie/find/$it", MovieEntity::class.java).body!!
            favorites.add(resp)
        }
        return ResponseEntity.ok(Response(UUID.fromString(randomId), favorites))

    }
}
