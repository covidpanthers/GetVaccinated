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
      var accessKeyId = System.getenv("AWS_ACCESS_KEY_ID");
      var awsSecretAccess = System.getenv("AWS_SECRET_ACCESS_KEY");
      var region = System.getenv("AWS_DEFAULT_REGION");
      if (accessKeyId == null) {
        accessKeyId = "";
      }
      if (awsSecretAccess == null) {
        awsSecretAccess = "";
      }
      var builder = AmazonDynamoDBClientBuilder
        .standard()
        .withCredentials(new AWSStaticCredentialsProvider(
          new BasicAWSCredentials(accessKeyId, awsSecretAccess)
        ));
      if (region == null) {
        builder = builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
          "http://localhost:8000", "us-west-2"
        ));
      }
      return builder.build();
    }
  }
