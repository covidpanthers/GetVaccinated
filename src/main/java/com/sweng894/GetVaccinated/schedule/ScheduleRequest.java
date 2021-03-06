package com.sweng894.GetVaccinated.schedule;

import com.sweng894.GetVaccinated.api.entity.Appointment;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;

public final class ScheduleRequest {
  private String name;
  private String email;
  private String time;
  private Integer month;
  private Integer day;

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public Integer getDay() {
    return day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Appointment toAppointment()
  {
    var appt = new Appointment();
    appt.setEmail(getEmail());
    appt.setName(getName());
    appt.setDate(String.format("2021-%s-%s", getMonth(), getDay()));
    appt.setConfirmationNumber(RandomStringUtils.randomAlphanumeric(16).toUpperCase(Locale.ROOT));
    return appt;
  }
}
