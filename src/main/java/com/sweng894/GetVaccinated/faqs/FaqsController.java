package com.sweng894.GetVaccinated.faqs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class FaqsController {
  @GetMapping("/faqs")
  public String index() {
    return "faqs";
  }
}
