package com.sweng894.GetVaccinated.api.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "getvaccinated")
public class Appointment {


  @DynamoDBHashKey(attributeName = "partitionKey")
  @DynamoDBAutoGeneratedKey
  private String confirmationNumber;

  @DynamoDBRangeKey(attributeName = "sortKey")
  private String email;

  @DynamoDBAttribute(attributeName = "type")
  private String type = "appointment";

  @DynamoDBAttribute(attributeName="name")
  private String name;

  @DynamoDBAttribute(attributeName="locationId")
  private String locationId;

  @DynamoDBAttribute(attributeName="status")
  private String status;

  @DynamoDBRangeKey(attributeName="date")
  private String date;

}
