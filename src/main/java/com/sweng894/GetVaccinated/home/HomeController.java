package com.sweng894.GetVaccinated.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  @GetMapping("/")
  public String index(@RequestParam(value = "name", defaultValue = "World") String name, Model model) {
    model.addAttribute("message", name);
    return "home";
  }
}
