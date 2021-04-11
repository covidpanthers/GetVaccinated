package com.sweng894.GetVaccinated.api.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "getvaccinated")
public class HealthDepartment {

  @DynamoDBHashKey(attributeName="partitionKey")
  private String id;

  @DynamoDBRangeKey(attributeName="sortKey")
  private String state;

  @DynamoDBAttribute
  private String phase;

  @DynamoDBAttribute
  private String status;

  @DynamoDBAttribute
  private List<String> description;

}
