package com.sweng894.GetVaccinated.vaccine;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.sweng894.GetVaccinated.api.entity.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
