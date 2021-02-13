package com.sweng894.GetVaccinated.schedule;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public final class ScheduleController {
  private final ScheduleRepository repository;

  public ScheduleController(ScheduleRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/schedule")
  public String get(Model model) {
    var request = new ScheduleRequest();
    request.setStartTime("");
    model.addAttribute("request", request);
    return "schedule/index";
  }
  @PostMapping("/schedule")
  public String post(@ModelAttribute ScheduleRequest request, Model model) {
    // TODO: add failed request path
    var confirmation = this.repository.saveRequest(request);
    model.addAttribute("confirmation", confirmation);
    return "schedule/confirmation";
  }
}
