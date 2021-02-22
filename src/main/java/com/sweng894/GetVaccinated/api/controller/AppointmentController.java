package com.sweng894.GetVaccinated.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sweng894.GetVaccinated.api.repository.AppointmentRepository;
import com.sweng894.GetVaccinated.api.entity.Appointment;

@RestController
public class AppointmentController {
  @Autowired
  private AppointmentRepository appointmentRepository;

  @PostMapping("/appointment")
  public Appointment saveAppointment(@RequestBody Appointment appointment) {
    return appointmentRepository.save(appointment);
  }

  @GetMapping("/appointment/{confirmationNumber}/{email}")
  public Appointment getAppointmentConfirmation(@PathVariable("confirmationNumber") String confirmationNumber, @PathVariable("email") String email) {
    return appointmentRepository.getAppointmentConfirmation(confirmationNumber, email);
  }

  @DeleteMapping("/appointment/{confirmationNumber}/{email}")
  public String deleteAppointment(@PathVariable("confirmationNumber") String confirmationNumber, @PathVariable("email") String email) {
    return appointmentRepository.delete(confirmationNumber, email);
  }

  @PutMapping("/appointment/{confirmationNumber}")
  public String updateAppointment(@PathVariable("confirmationNumber") String confirmationNumber, @RequestBody Appointment appointment) {
    return appointmentRepository.update(confirmationNumber, appointment);
  }
}
