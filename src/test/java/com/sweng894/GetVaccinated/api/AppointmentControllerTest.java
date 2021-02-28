package com.sweng894.GetVaccinated.api;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import com.sweng894.GetVaccinated.api.controller.AppointmentController;
import com.sweng894.GetVaccinated.api.repository.AppointmentRepository;
import com.sweng894.GetVaccinated.api.entity.Appointment;

@Tag("integration")
@SpringBootTest
public class AppointmentControllerTest {
  @Tested
  private AppointmentController appointmentController;

  @Injectable
  AppointmentRepository repository;

  @Test
  public void testSaveAppointmentSuccess() {
    new Expectations() {{
      repository.save(getGenericAppointment());
      result = getGenericAppointment();
    }};

    Appointment expected = new Appointment(
      "David Sweeney",
      "TEST_CONFIRMATION_NUMBER",
      "test@abc.com",
      "CONFIRMED",
      "2021-04-09 21:00:00"
    );

    expected = appointmentController.saveAppointment(expected);
    Assertions.assertEquals(expected.getConfirmationNumber(),
      appointmentController.saveAppointment(expected).getConfirmationNumber());
  }

  @Test
  public void testGetAppointmentConfirmationSuccess() {
    new Expectations(){{
      repository.getAppointmentConfirmation("TEST_CONFIRMATION_NUMBER",
        "test@abc.com");
      result = getGenericAppointment();
    }};

    Appointment expected = new Appointment(
      "David Sweeney",
      "TEST_CONFIRMATION_NUMBER",
      "test@abc.com",
      "CONFIRMED",
      "2021-04-09 21:00:00"
    );

    Assertions.assertEquals(expected.getConfirmationNumber(),
      appointmentController.getAppointmentConfirmation(expected.getConfirmationNumber(),
        expected.getEmail()).getConfirmationNumber());
  }

  @Test
  public void testDeleteAppointmentSuccess() {
    new Expectations(){{
      repository.delete("TEST_CONFIRMATION_NUMBER",
        "test@abc.com");
      result = "Appointment Cancelled!";
    }};

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
    Appointment actual = new Appointment(
      "David Sweeney",
      "TEST_CONFIRMATION_NUMBER",
      "test@abc.com",
      "CONFIRMED",
      "2021-04-09 21:00:00"
    );
    new Expectations(){{
      repository.update("TEST_CONFIRMATION_NUMBER", actual);
      result = "Appointment Cancelled!";
    }};

    Appointment expected = new Appointment(
      "David Sweeney",
      "TEST_CONFIRMATION_NUMBER",
      "test@abc.com",
      "CONFIRMED",
      "2021-04-09 21:00:00"
    );

    Assertions.assertEquals("Appointment Cancelled!",
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
