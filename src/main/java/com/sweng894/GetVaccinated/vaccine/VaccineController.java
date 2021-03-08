package com.sweng894.GetVaccinated.vaccine;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VaccineController {
  private final VaccineRepository vaccineRepository;

  public VaccineController(VaccineRepository vaccineRepository) {
    this.vaccineRepository = vaccineRepository;
  }

  @GetMapping("/vaccines")
  public String getAllVaccines(Model model) {
    model.addAttribute("vaccines", vaccineRepository.getAll());
    return "vaccine/index.html";
  }
}
