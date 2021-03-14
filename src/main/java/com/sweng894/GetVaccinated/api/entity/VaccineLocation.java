package com.sweng894.GetVaccinated.api.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class VaccineLocation {
  @DynamoDBAttribute
  private String location;
  @DynamoDBAttribute
  private int availabilityCount;
}
