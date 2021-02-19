package com.sweng894.GetVaccinated.schedule;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
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
}
