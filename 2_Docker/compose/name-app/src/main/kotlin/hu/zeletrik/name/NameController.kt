package hu.zeletrik.name

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/name")
class NameController {

    val names = listOf("Captain America", "Iron Man", "Black Widow", "Hawkeye", "Hulk", "Dr. Strange", "Spiderman")

    companion object {
        private const val Random = "/random"
    }

    @GetMapping(Random)
    fun random() =
            names.shuffled().take(1)[0]

}
