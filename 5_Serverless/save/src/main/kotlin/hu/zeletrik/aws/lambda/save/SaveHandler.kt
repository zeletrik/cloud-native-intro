package hu.zeletrik.aws.lambda.save

import com.amazonaws.regions.Regions
import java.io.*
import com.fasterxml.jackson.module.kotlin.*
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import java.util.*

data class HandlerOutput(val message: String)

class SaveHandler {
    private val mapper = jacksonObjectMapper()

    fun save(input: InputStream, output: OutputStream) {
        val inputObj = mapper.readValue<MovieEntity>(input)
        inputObj.movieId = UUID.randomUUID().toString()
        val dynamoDBMapper = DynamoDBMapper(amazonDynamoDBClient())
        dynamoDBMapper.save(inputObj)

        mapper.writeValue(output, HandlerOutput("Movie with id=${inputObj.movieId} saved"))
    }

    fun amazonDynamoDBClient() =
            AmazonDynamoDBClientBuilder.standard()
                    .withRegion(Regions.US_WEST_2)
                    .build();
}