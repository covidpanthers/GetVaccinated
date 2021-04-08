package com.sweng894.GetVaccinated.api.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.sweng894.GetVaccinated.api.entity.Appointment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class DynamoDbTestConfiguration {
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
          new BasicAWSCredentials("DUMMY_ACCESS_KEY", "DUMMY_SECRET_KEY")
        )).build();
    } else {

      return AmazonDynamoDBClientBuilder
        .standard()
        .withRegion(region)
        .build();
    }
  }

  public void createTable(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDB) {
    var req = dynamoDBMapper.generateCreateTableRequest(Appointment.class);
    req.setBillingMode("PAY_PER_REQUEST");
    try {
      dynamoDB.createTable(req);
    } catch (ResourceInUseException ignored) {
    }
  }
}
