package com.sweng894.GetVaccinated.api.controller;


import com.sweng894.GetVaccinated.api.entity.Vaccine;
import com.sweng894.GetVaccinated.api.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VaccineController {
  @Autowired
  private VaccineRepository VaccineRepository;

  @PostMapping("/vaccine")
  public Vaccine saveVaccine(@RequestBody Vaccine Vaccine) {
    return VaccineRepository.save(Vaccine);
  }

  @GetMapping("/Vaccine/{vaccineId}")
  public Vaccine getVaccine(@PathVariable("vaccineId") int vaccineId) {
    return VaccineRepository.getById(vaccineId);
  }
}
