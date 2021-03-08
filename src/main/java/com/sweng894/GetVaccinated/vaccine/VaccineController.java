package com.sweng894.GetVaccinated.vaccine;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class VaccineController {
  private final VaccineRepository vaccineRepository;

  public VaccineController(VaccineRepository vaccineRepository) {
    this.vaccineRepository = vaccineRepository;
  }

  @GetMapping("/vaccines")
  public String getAllVaccines(@RequestParam(required = false) String vaccineId, Model model) {
    model.addAttribute("vaccines", vaccineRepository.getAll());
    Collection<com.sweng894.GetVaccinated.api.entity.VaccineLocation> locations = new ArrayList<>();
    if(vaccineId != null) {
      locations.addAll(vaccineRepository.getVaccineLocationsByVaccineId(vaccineId));
    }
    model.addAttribute("locations", locations);
    return "vaccine/index.html";
  }
}
