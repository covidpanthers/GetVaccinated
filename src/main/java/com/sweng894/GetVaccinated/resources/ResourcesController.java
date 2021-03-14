package com.sweng894.GetVaccinated.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class ResourcesController {
  @GetMapping("/resources")
  public String index() {
    return "/resources";
  }
}
