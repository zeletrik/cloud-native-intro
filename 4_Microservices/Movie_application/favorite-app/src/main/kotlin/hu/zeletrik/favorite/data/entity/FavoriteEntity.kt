package hu.zeletrik.favorite.data.entity

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

@DynamoDBTable(tableName = "Favorites")
data class FavoriteEntity @JvmOverloads constructor(
        @DynamoDBHashKey(attributeName = "user_ID")
        var userId: UUID? = null,
        var favorites: MutableList<UUID> = ArrayList()
)