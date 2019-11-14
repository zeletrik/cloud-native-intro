package hu.zeletrik.aws.lambda.load

import com.amazonaws.regions.Regions
import java.io.*
import com.fasterxml.jackson.module.kotlin.*
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper

data class HandlerInput(val movieId: String)

class LoadHandler {
    private val mapper = jacksonObjectMapper()

    fun load(input: InputStream, output: OutputStream) {
        val inputObj = mapper.readValue<HandlerInput>(input)
        val dynamoDBMapper = DynamoDBMapper(amazonDynamoDBClient())
        val result = dynamoDBMapper.load(MovieEntity::class.java, inputObj.movieId)

        mapper.writeValue(output, result)
    }

    fun amazonDynamoDBClient() =
            AmazonDynamoDBClientBuilder.standard()
                    .withRegion(Regions.US_WEST_2)
                    .build();
}