package hu.zeletrik.movie.data.entity

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import java.time.Instant
import java.util.*

@DynamoDBTable(tableName = "Movies")
data class MovieEntity @JvmOverloads constructor(
        @DynamoDBHashKey(attributeName = "movie_ID")
        var movieId: UUID? = null,
        var title: String = "",
        var release: String = "",
        var stars: List<String>? = ArrayList()
)