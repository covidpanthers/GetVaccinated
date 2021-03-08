package com.sweng894.GetVaccinated.api.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.util.Date;
@ToString
@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "Vaccine")
public class Vaccine {
  @DynamoDBHashKey(attributeName = "id")
  private int id;
  @DynamoDBRangeKey(attributeName = "companyId")
  private int companyId;
  @DynamoDBAttribute
  private String title;
  @DynamoDBAttribute
  private Date launchDate;
  @DynamoDBAttribute
  private int noOfShots;
  @DynamoDBAttribute
  private String distributionProcess;
}
