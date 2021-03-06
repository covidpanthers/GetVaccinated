package com.sweng894.GetVaccinated.api;

import com.sweng894.GetVaccinated.api.entity.Appointment;
import com.sweng894.GetVaccinated.api.repository.AppointmentRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("integration")
@SpringBootTest
public final class AppointmentRepositoryTests {
  @Autowired
  private AppointmentRepository repository;

  @Test
  public void createAppointment() {
    try {
      var appt1 = new Appointment(
        "David Sweeney",
        "TEST_CONFIRMATION_NUMBER",
        "test@abc.com",
        "CONFIRMED",
        "2021-04-09 21:00:00"
      );
      repository.save(appt1);
      var appt2 = repository.getAppointmentConfirmation("TEST_CONFIRMATION_NUMBER", "test@abc.com");
      assertEquals(appt2.getConfirmationNumber(), "TEST_CONFIRMATION_NUMBER");
    } finally {
      repository.delete("TEST_CONFIRMATION_NUMBER", "test@abc.com");
    }
  }
}
