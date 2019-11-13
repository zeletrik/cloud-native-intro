package hu.zeletrik.aws.lambda.save

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import java.util.*

@DynamoDBTable(tableName = "Movies")
data class MovieEntity @JvmOverloads constructor(

        @DynamoDBHashKey(attributeName = "movie_ID")
        var movieId: String = "",
        var title: String = "",
        var release: String = "",
        var stars: List<String> = ArrayList()
)