package hu.zeletrik.favorite.data

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import hu.zeletrik.favorite.data.entity.FavoriteEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class FavoriteRepository
(@Autowired val dynamoDBMapper: DynamoDBMapper){

    fun findAll() =
         dynamoDBMapper.scan(FavoriteEntity::class.java, DynamoDBScanExpression())


    fun findById(id: String): Optional<FavoriteEntity> {
        val uid = UUID.fromString(id)
        val result = dynamoDBMapper.load(FavoriteEntity::class.java, uid)
        return Optional.ofNullable(result)
    }

    fun save(entity: FavoriteEntity) {
        dynamoDBMapper.save(entity)
    }
}

