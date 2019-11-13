package hu.zeletrik.chain.data.entity

import kotlin.collections.ArrayList

data class FavoriteEntity(
        var userId: String? = "",
        var favorites: List<String> = ArrayList()
)