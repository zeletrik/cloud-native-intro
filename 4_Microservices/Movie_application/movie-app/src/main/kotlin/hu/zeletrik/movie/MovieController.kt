package hu.zeletrik.movie

import hu.zeletrik.movie.data.MovieRepository
import hu.zeletrik.movie.data.entity.MovieEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.*



@RestController
@RequestMapping("/movie")
class MovieController(
        val movieRepository: MovieRepository,
        val restTemplate: RestTemplate) {

    companion object {
        private const val MovieById = "/find/{id}"
        private const val AllMovie = "/find/all"
        private const val AddMovie = "/add"
        private const val RandomMovie = "/random"
    }

    @GetMapping(MovieById)
    fun getMovie(@PathVariable id: String) =
            movieRepository.findById(id)
                    .map { ResponseEntity.ok(it) }
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build())

    @GetMapping(AllMovie)
    fun getAllMovie() = movieRepository.findAll()

    @PostMapping(AddMovie)
    fun addMovie(@RequestBody movieEntity: MovieEntity): ResponseEntity<String> {
        movieRepository.save(movieEntity)
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build()
    }

    @GetMapping(RandomMovie)
    fun random(): ResponseEntity<MovieEntity> {
        val movieList = restTemplate.getForEntity("http://movie-service:3000/movie/find/all",  Array<MovieEntity>::class.java).body!!.asList()
        val randomId = movieList.shuffled().take(1)[0].movieId
        return restTemplate.getForEntity("http://movie-service:3000/movie/find/$randomId",  MovieEntity::class.java)
    }
}
