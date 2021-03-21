package com.sweng894.GetVaccinated.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.sweng894.GetVaccinated.api.controller.AppointmentController;
import com.sweng894.GetVaccinated.api.entity.Appointment;

@Tag("integration")
@SpringBootTest
public class AppointmentControllerTest {
  @Autowired
  private AppointmentController appointmentController;

  @Test
  public void testSaveAppointmentSuccess() {
    Appointment expected = new Appointment(
      "David Sweeney",
      "TEST_CONFIRMATION_NUMBER",
      "test@abc.com",
      "CONFIRMED",
      "2021-04-09 21:00:00"
    );

    appointmentController.saveAppointment(expected);
    var expectedConfirmation = expected.getConfirmationNumber();
    var actualConfirmation = appointmentController.getAppointmentConfirmation(expectedConfirmation, expected.getEmail()).getConfirmationNumber();
    Assertions.assertEquals(expectedConfirmation, actualConfirmation);
  }

  @Test
  public void testDeleteAppointmentSuccess() {
    Appointment expected = new Appointment(
      "David Sweeney",
      "TEST_CONFIRMATION_NUMBER",
      "test@abc.com",
      "CONFIRMED",
      "2021-04-09 21:00:00"
    );

    Assertions.assertEquals("Appointment Cancelled!",
      appointmentController.deleteAppointment(expected.getConfirmationNumber(),
        expected.getEmail()));
  }

  @Test
  public void testUpdateAppointmentSuccess() {
    appointmentController.saveAppointment(new Appointment(
      "David Sweeney",
      "TEST_CONFIRMATION_NUMBER",
      "test@abc.com",
      "CONFIRMED",
      "2021-04-09 21:00:00"
    ));

    Appointment expected = new Appointment(
      "David Sweeney",
      "TEST_CONFIRMATION_NUMBER",
      "test@abc.com",
      "CONFIRMED",
      "2021-04-09 21:00:00"
    );

    Assertions.assertEquals("TEST_CONFIRMATION_NUMBER",
      appointmentController.updateAppointment(expected.getConfirmationNumber(),
        expected));
  }

  private Appointment getGenericAppointment() {
    Appointment output = new Appointment(
      "David Sweeney",
      "TEST_CONFIRMATION_NUMBER",
      "test@abc.com",
      "CONFIRMED",
      "2021-04-09 21:00:00"
    );

    return output;
  }
}
