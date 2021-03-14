package com.sweng894.GetVaccinated.vaccine;

import com.sweng894.GetVaccinated.api.entity.Vaccine;
import com.sweng894.GetVaccinated.api.entity.VaccineLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class VaccineController {
  @Autowired
  private final VaccineRepository vaccineRepository;

  public VaccineController(VaccineRepository vaccineRepository) {
    this.vaccineRepository = vaccineRepository;
  }

  @GetMapping("/vaccines")
  public String getAllVaccines(@RequestParam(required = false) String vaccineId, Model model) {
    model.addAttribute("vaccines", vaccineRepository.getAll());
    if(vaccineId != null) {
      model.addAttribute("selectedVaccine", vaccineRepository.getById(vaccineId));
    }
    return "vaccine/index.html";
  }
}
