package hu.zeletrik.authapp.data

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import hu.zeletrik.authapp.data.entity.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepository
(@Autowired val dynamoDBMapper: DynamoDBMapper){

    fun findAll() =
            dynamoDBMapper.scan(UserEntity::class.java, DynamoDBScanExpression())


    fun findById(id: String): Optional<UserEntity> {
        val uid = UUID.fromString(id)
        val result = dynamoDBMapper.load(UserEntity::class.java, uid)
        return Optional.ofNullable(result)
    }

    fun save(entity: UserEntity) {
        dynamoDBMapper.save(entity)
    }

}
