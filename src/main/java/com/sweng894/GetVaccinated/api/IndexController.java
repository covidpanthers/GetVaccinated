package com.sweng894.GetVaccinated.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class IndexController {
  @GetMapping("/api")
  public Greeting index(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new Greeting(1, name);
  }
}
