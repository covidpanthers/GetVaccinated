package com.sweng894.GetVaccinated.schedule;


import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Locale;

@Controller
public final class ScheduleController {
  private final ScheduleRepository repository;

  public ScheduleController(ScheduleRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/schedule")
  public String get(@RequestParam(required = false) Integer month,
                    @RequestParam(required = false) Integer day,
                    Model model) {
    if (month == null) {
      month = LocalDateTime.now().getMonthValue();
    }
    var weeks = Week.weeksOfMonth(2021, month);
    // TODO: retrieve this data dynamically based upon availability for the date
    var times = Arrays.asList("12:00 PM", "1:00 PM", "2:00 PM");
    var request = new ScheduleRequest();
    request.setTime("");
    request.setMonth(month);
    request.setDay(day);
    model.addAttribute("times", times);
    model.addAttribute("request", request);
    model.addAttribute("monthDisplayName", Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH));
    model.addAttribute("month", month);
    model.addAttribute("day", day);
    model.addAttribute("previousMonth", month == 1 ? 1 : month - 1);
    model.addAttribute("nextMonth", month == 12 ? 12 : month + 1);
    model.addAttribute("weeks", weeks);
    return "schedule/index";
  }
  @PostMapping("/schedule")
  public String post(@ModelAttribute ScheduleRequest request, Model model) {
    // TODO: add failed request path
    // TODO: calendar invite
    var confirmation = this.repository.saveRequest(request);
    model.addAttribute("confirmation", confirmation);
    return "schedule/confirmation";
  }

  @GetMapping("/schedule/confirmation/{confirmationNumber}.ics")
  public ResponseEntity<String> downloadCalendarInvite(@PathVariable String confirmationNumber) throws SocketException {
    var scheduleRequest = repository.getRequest(confirmationNumber);
    if (scheduleRequest == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Confirmation number not found.");
    }
    var icsCalendar = new Calendar();
    icsCalendar.getProperties().add(new ProdId("-//sweng894//GetVaccinated//EN"));
    icsCalendar.getProperties().add(Version.VERSION_2_0);
    icsCalendar.getProperties().add(CalScale.GREGORIAN);

    var startDate = new GregorianCalendar();
    // startDate.setTimeZone(timezone);
    startDate.set(java.util.Calendar.MONTH, scheduleRequest.getMonth());
    startDate.set(java.util.Calendar.DAY_OF_MONTH, scheduleRequest.getDay());
    startDate.set(java.util.Calendar.YEAR, 2021);
    // TODO: do not hardcode hour of day
    startDate.set(java.util.Calendar.HOUR_OF_DAY, 12);
    startDate.set(java.util.Calendar.MINUTE, 0);
    startDate.set(java.util.Calendar.SECOND, 0);

    var endDate = new GregorianCalendar();
    // startDate.setTimeZone(timezone);
    startDate.set(java.util.Calendar.MONTH, scheduleRequest.getMonth());
    startDate.set(java.util.Calendar.DAY_OF_MONTH, scheduleRequest.getDay());
    startDate.set(java.util.Calendar.YEAR, 2021);
    // TODO: do not hardcode hour of day
    startDate.set(java.util.Calendar.HOUR_OF_DAY, 13);
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
}
