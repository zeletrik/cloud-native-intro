package hu.zeletrik.favorite

import hu.zeletrik.favorite.data.FavoriteRepository
import hu.zeletrik.favorite.data.entity.FavoriteEntity
import io.micrometer.core.annotation.Timed
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.*

@RestController
@RequestMapping("/favorite")
class FavoriteController(
        val favoriteRepository: FavoriteRepository) {

    companion object {
        private const val FavoriteById = "/find/{id}"
        private const val AllFavorite = "/find/all"
        private const val AddFavorite = "/add/{id}?toUser={userId}"
    }

    @GetMapping(FavoriteById)
    @Timed(value = "get_favorite_by_user_id")
    fun getFavorite(@PathVariable id: String) =
            favoriteRepository.findById(id)
                    .map { ResponseEntity.ok(it) }
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build())

    @GetMapping(AllFavorite)
    @Timed(value = "get_all_favorite")
    fun getAllFavorite() = favoriteRepository.findAll()

    @PostMapping(AddFavorite)
    @Timed(value = "add_favorite_to_user")
    fun addFavorite(@PathVariable id: String, @RequestParam userId: String): ResponseEntity<Any> {
         favoriteRepository.findById(id).ifPresent {
          it.favorites.add(UUID.fromString(userId))
            favoriteRepository.save(it)
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build()
    }
}
