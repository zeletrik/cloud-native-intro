package hu.zeletrik.movie.data

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression
import hu.zeletrik.movie.data.entity.MovieEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MovieRepository
(@Autowired val dynamoDBMapper: DynamoDBMapper){

    fun findAll() =
         dynamoDBMapper.scan(MovieEntity::class.java, DynamoDBScanExpression())


    fun findById(id: String): Optional<MovieEntity> {
        val uid = UUID.fromString(id)
        val result = dynamoDBMapper.load(MovieEntity::class.java, uid)
        return Optional.ofNullable(result)
    }

    fun save(entity: MovieEntity) {
        dynamoDBMapper.save(entity)
    }

}

