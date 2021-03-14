package com.sweng894.GetVaccinated.vaccine;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.sweng894.GetVaccinated.api.entity.Appointment;
import com.sweng894.GetVaccinated.api.entity.Vaccine;
import com.sweng894.GetVaccinated.api.entity.VaccineLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VaccineRepository {
  private DynamoDBMapper dynamoDBMapper;

  public VaccineRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDB) {
    var req = dynamoDBMapper.generateCreateTableRequest(Vaccine.class);
    req.setBillingMode("PAY_PER_REQUEST");
    try {
      dynamoDB.createTable(req);
    } catch (ResourceInUseException ignored) {
    }
    this.dynamoDBMapper = dynamoDBMapper;
  }

  public Vaccine save(Vaccine Vaccine){
    dynamoDBMapper.save(Vaccine);
    return Vaccine;
  }

  public Vaccine getById(String id) {
    return dynamoDBMapper.load(Vaccine.class, id);
  }

  public List<Vaccine> getAll() {
    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
      .withConsistentRead(false);
    return dynamoDBMapper.scan(Vaccine.class, scanExpression);
  }
}
