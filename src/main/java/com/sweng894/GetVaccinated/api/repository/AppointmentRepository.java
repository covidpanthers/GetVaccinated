package com.sweng894.GetVaccinated.api.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.sweng894.GetVaccinated.api.entity.Appointment;
import org.springframework.stereotype.Repository;

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
