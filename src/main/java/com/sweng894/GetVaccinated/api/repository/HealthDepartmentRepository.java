package com.sweng894.GetVaccinated.api.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.sweng894.GetVaccinated.api.entity.Appointment;
import com.sweng894.GetVaccinated.api.entity.HealthDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HealthDepartmentRepository {
  @Autowired
  private DynamoDBMapper dynamoDBMapper;

//  public HealthDepartmentRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDB) {
//    var req = dynamoDBMapper.generateCreateTableRequest(HealthDepartment.class);
//    req.setBillingMode("PAY_PER_REQUEST");
//    try {
//      dynamoDB.createTable(req);
//    } catch (ResourceInUseException ignored) {
//    }
//    this.dynamoDBMapper = dynamoDBMapper;
//  }

  public HealthDepartment save(HealthDepartment healthDept){
    dynamoDBMapper.save(healthDept);
    return healthDept;
  }

  public HealthDepartment get(String id, String state) {
    HealthDepartment output = dynamoDBMapper.load(HealthDepartment.class, id, state);
    return output;
  }

}
