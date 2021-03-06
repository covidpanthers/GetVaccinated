package com.sweng894.GetVaccinated.schedule;

import com.sweng894.GetVaccinated.api.entity.Appointment;
import com.sweng894.GetVaccinated.api.repository.AppointmentRepository;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.SocketException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Locale;

@Controller
public final class ScheduleController {
  @Autowired
  private final AppointmentRepository repository;

  public ScheduleController(AppointmentRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/schedule")
  public String get(@RequestParam(required = false) Integer month,
                    @RequestParam(required = false) Integer day,
                    Model model) {
    if (month == null) {
      month = LocalDateTime.now().getMonthValue();
    }
    var m = new ScheduleModel();
    m.setTime("");
    m.setMonth(month);
    m.setDay(day);
    model.addAttribute("monthDisplayName", Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH));
    model.addAttribute("previousMonth", month == 1 ? 1 : month - 1);
    model.addAttribute("nextMonth", month == 12 ? 12 : month + 1);
    model.addAttribute("m", m);
    return "schedule/index";
  }

  @PostMapping("/schedule")
  public String post(@ModelAttribute ScheduleRequest request, Model model) {
    var appointment = request.toAppointment();
    var confirmation = this.repository.save(appointment);
    var confirmationNumber = "";
    if(confirmation != null) {
      confirmationNumber = confirmation.getConfirmationNumber();
    }
    model.addAttribute("confirmation", confirmationNumber);
    return "redirect:/schedule/" + confirmationNumber;
  }

  @GetMapping("/schedule/{confirmationNumber}")
  public String getConfirmation(@PathVariable String confirmationNumber, Model model) {
    var appointment = repository.getAppointmentByConfirmationNumber(confirmationNumber);
    if (appointment == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Confirmation number not found.");
    }
    model.addAttribute("confirmationNumber", confirmationNumber);
    model.addAttribute("timing", appointment.getDate());
    return "schedule/confirmation";
  }

  @GetMapping("/schedule/confirmation/{confirmationNumber}.ics")
  public ResponseEntity<String> downloadCalendarInvite(@PathVariable String confirmationNumber) throws SocketException {
    var scheduleRequest = repository.getAppointmentByConfirmationNumber(confirmationNumber);
    if (scheduleRequest == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Confirmation number not found.");
    }
    var date = scheduleRequest.getDate().split(" ")[0];
    var parts = scheduleRequest.getDate().split(" ")[1].split(":");
    var hour = Integer.parseInt(parts[0]);
    var icsCalendar = new Calendar();
    icsCalendar.getProperties().add(new ProdId("-//sweng894//GetVaccinated//EN"));
    icsCalendar.getProperties().add(Version.VERSION_2_0);
    icsCalendar.getProperties().add(CalScale.GREGORIAN);

    var startDate = new GregorianCalendar();
    startDate.set(java.util.Calendar.MONTH, Integer.parseInt(date.split("-")[1]));
    startDate.set(java.util.Calendar.DAY_OF_MONTH, Integer.parseInt(date.split("-")[2]));
    startDate.set(java.util.Calendar.YEAR, 2021);
    startDate.set(java.util.Calendar.HOUR_OF_DAY, hour);
    startDate.set(java.util.Calendar.MINUTE, 0);
    startDate.set(java.util.Calendar.SECOND, 0);

    var endDate = new GregorianCalendar();
    startDate.set(java.util.Calendar.MONTH, Integer.parseInt(date.split("-")[1]));
    startDate.set(java.util.Calendar.DAY_OF_MONTH, Integer.parseInt(date.split("-")[2]));
    startDate.set(java.util.Calendar.YEAR, 2021);
    startDate.set(java.util.Calendar.HOUR_OF_DAY, hour + 1);
    startDate.set(java.util.Calendar.MINUTE, 0);
    startDate.set(java.util.Calendar.SECOND, 0);

    var eventName = "1st Vaccination";
    var start = new DateTime(startDate.getTime());
    var end = new DateTime(endDate.getTime());
    var meeting = new VEvent(start, end, eventName);

    var ug = new UidGenerator("uidGen");
    var uid = ug.generateUid();
    meeting.getProperties().add(uid);
    icsCalendar.getComponents().add(meeting);

    var body = icsCalendar.toString();
    var headers = new HttpHeaders();
    headers.add("Content-Type", "text/calendar");
    return new ResponseEntity<>(body, headers, HttpStatus.OK);
  }

  @GetMapping("/schedule/ineligible")
  public String getIneligiblePage() {
    return "schedule/ineligible";
  }

  @GetMapping("/schedule/eligible")
  public String getEligiblePage() {
    return "schedule/eligible";
  }

  @GetMapping("schedule/appointment")
  public String getCheckPage() {
    return "schedule/check";
  }

  @GetMapping("/schedule/appointment/{confirmationNumber}/{email}/edit")
  public String getAppointmentEditPage() {
    return "schedule/edit-appointment";
  }
}
