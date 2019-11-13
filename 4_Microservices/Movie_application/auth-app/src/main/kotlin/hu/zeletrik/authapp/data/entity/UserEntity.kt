package hu.zeletrik.authapp.data.entity

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import java.util.*

@DynamoDBTable(tableName = "Users")
data class UserEntity @JvmOverloads constructor(
        @DynamoDBHashKey(attributeName = "user_ID")
        var userId: UUID? = null,
        var name: String = "",
        var password: String = ""
)