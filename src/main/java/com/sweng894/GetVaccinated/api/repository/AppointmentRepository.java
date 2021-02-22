package com.sweng894.GetVaccinated.api.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sweng894.GetVaccinated.api.entity.Appointment;

@Repository
public class AppointmentRepository {
  @Autowired
  private DynamoDBMapper dynamoDBMapper;

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
