package com.sweng894.GetVaccinated.api.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DynamoDbConfiguration {
    @Bean
    public DynamoDBMapper dynamoDBMapper() {
      return new DynamoDBMapper(amazonDynamoDB());
    }
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
      var region = System.getenv("AWS_DEFAULT_REGION");
      if (region == null) {
        return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
          "http://localhost:8000", "us-west-2"
        ))
        .withCredentials(new AWSStaticCredentialsProvider(
          new BasicAWSCredentials("", "")
        )).build();
      } else {
        return AmazonDynamoDBClientBuilder
          .standard()
          .withRegion(region)
          .build();
      }
    }
  }
