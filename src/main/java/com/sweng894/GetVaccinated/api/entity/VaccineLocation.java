package com.sweng894.GetVaccinated.api.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "VaccineLocation")
public class VaccineLocation {
  @DynamoDBHashKey(attributeName = "id")
  private int id;
  @DynamoDBRangeKey(attributeName = "vaccineId")
  private int vaccineId;
  @DynamoDBAttribute
  private String location;
  @DynamoDBAttribute
  private int availabilityCount;
}
