package com.sweng894.GetVaccinated.vaccine;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.sweng894.GetVaccinated.api.entity.Vaccine;
import com.sweng894.GetVaccinated.api.entity.VaccineLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VaccineRepository {
  @Autowired
  private DynamoDBMapper dynamoDBMapper;

  public Vaccine save(Vaccine Vaccine){
    dynamoDBMapper.save(Vaccine);
    return Vaccine;
  }

  public Vaccine getById(int id) {
    return dynamoDBMapper.load(Vaccine.class, id);
  }

  public List<Vaccine> getAll() {
    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
      .withIndexName("id")
      .withConsistentRead(false);
    return dynamoDBMapper.scan(Vaccine.class, scanExpression);
  }

  public List<VaccineLocation> getVaccineLocationsByVaccineId(String vaccineId) {
    Map<String, AttributeValue> attributeValues = new HashMap<>();
    attributeValues.put(":vaccineId", new AttributeValue().withN(vaccineId));

    DynamoDBQueryExpression<VaccineLocation> dynamoDBQueryExpression = new DynamoDBQueryExpression<VaccineLocation>()
      .withKeyConditionExpression("vaccineId = :vaccineId")
      .withExpressionAttributeValues(attributeValues);
    return dynamoDBMapper.query(VaccineLocation.class, dynamoDBQueryExpression);
  }
}
