package com.sweng894.GetVaccinated.api.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName= "getvaccinated")
public class Location {

  @DynamoDBHashKey(attributeName = "partitionKey")
  private String locationId;

  @DynamoDBRangeKey(attributeName = "sortKey")
  private String geohash;

  @DynamoDBAttribute
  private String type;

  @DynamoDBAttribute
  private String name;

  @DynamoDBAttribute
  private String address;

  @DynamoDBAttribute
  private String address2;

  @DynamoDBAttribute
  private String city;

  @DynamoDBAttribute
  private String state;

  @DynamoDBAttribute
  private String zip;

  @DynamoDBAttribute
  private String county;

  @DynamoDBAttribute
  private String phone;

  @DynamoDBAttribute
  private String link;

  @DynamoDBAttribute
  private double latitude;

  @DynamoDBAttribute
  private double longitude;

}
