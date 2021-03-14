package com.sweng894.GetVaccinated.api.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Company {
  @DynamoDBAttribute
  private String name;
  @DynamoDBAttribute
  private String address;
}
