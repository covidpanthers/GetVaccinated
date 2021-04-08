package com.sweng894.GetVaccinated.api.controller;

import com.sweng894.GetVaccinated.api.entity.HealthDepartment;
import com.sweng894.GetVaccinated.api.repository.HealthDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/healthdept")
public class HealthDepartmentController {
  @Autowired
  HealthDepartmentRepository healthDepartmentRepository;

  @PostMapping
  public HealthDepartment addHealthDepartment(@RequestBody HealthDepartment healthDept) {
    return healthDepartmentRepository.save(healthDept);
  }

  @GetMapping("/{id}/{state}")
  public HealthDepartment getHealthDepartment(@PathVariable("id") String id, @PathVariable("state") String state) {
    return healthDepartmentRepository.get(id, state);
  }
}
