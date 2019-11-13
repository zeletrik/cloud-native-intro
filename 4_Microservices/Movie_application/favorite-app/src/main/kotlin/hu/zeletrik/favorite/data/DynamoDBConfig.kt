package hu.zeletrik.favorite.data

import org.springframework.context.annotation.Configuration
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import org.springframework.context.annotation.Bean

@Configuration
class DynamoDBConfig {

    val amazonDynamoDBEndpoint: String = "http://dynamo-db:8000"
    val amazonDynamoDBRegion: String = "local"

    @Bean
    fun amazonDynamoDBClient() =
            AmazonDynamoDBClientBuilder.standard()
                    .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBRegion))
                    .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials("", "")))
                    .build();

    @Bean
    fun dynamoDBMapper() = DynamoDBMapper(amazonDynamoDBClient());

}