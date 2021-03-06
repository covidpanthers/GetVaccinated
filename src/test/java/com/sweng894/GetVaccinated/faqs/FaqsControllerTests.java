package com.sweng894.GetVaccinated.faqs;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
public final class FaqsControllerTests {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getFaqs() throws Exception {
    mockMvc.perform(get("/faqs"))
      .andExpect(status().isOk());
  }
}
