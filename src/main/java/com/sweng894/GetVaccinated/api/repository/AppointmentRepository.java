package com.sweng894.GetVaccinated.api.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.sweng894.GetVaccinated.api.entity.Appointment;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AppointmentRepository {
  private final DynamoDBMapper dynamoDBMapper;

  public AppointmentRepository(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDB) {
    var req = dynamoDBMapper.generateCreateTableRequest(Appointment.class);
    req.setBillingMode("PAY_PER_REQUEST");
    try {
      dynamoDB.createTable(req);
    } catch (ResourceInUseException ignored) {
    }
    this.dynamoDBMapper = dynamoDBMapper;
  }

  public Appointment save(Appointment appointment){
    dynamoDBMapper.save(appointment);
    return appointment;
  }

  public Appointment getAppointmentConfirmation(String confirmationNumber, String email) {
    return dynamoDBMapper.load(Appointment.class, confirmationNumber, email);
  }

  public Appointment getAppointmentByConfirmationNumber(String confirmationNumber) {
    Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
    eav.put(":pk", new AttributeValue().withS(confirmationNumber));
    DynamoDBQueryExpression<Appointment> queryExpression = new DynamoDBQueryExpression<Appointment>()
      .withKeyConditionExpression("partitionKey = :pk").withExpressionAttributeValues(eav);
    var appointment = dynamoDBMapper.query(Appointment.class, queryExpression);
    return appointment.size() > 0 ? appointment.get(0) : null;
  }

  public String delete(String confirmationNumber, String email) {
    Appointment appointment = dynamoDBMapper.load(Appointment.class, confirmationNumber, email);
    dynamoDBMapper.delete(appointment);
    return "Appointment Cancelled!";
  }

  public String update(String confirmationNumber, Appointment appointment) {
    dynamoDBMapper.save(appointment,
      new DynamoDBSaveExpression()
        .withExpectedEntry("partitionKey",
          new ExpectedAttributeValue(
            new AttributeValue().withS(confirmationNumber)
          )));
    return confirmationNumber;
  }
}
