package com.sweng894.GetVaccinated.api.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.sweng894.GetVaccinated.api.entity.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
