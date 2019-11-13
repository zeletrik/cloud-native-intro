package hu.zeletrik.chain.data.entity

import java.util.*

data class MovieEntity(
        var movieId: String = "",
        var title: String = "",
        var release: String = "",
        var stars: List<String> = ArrayList()
)