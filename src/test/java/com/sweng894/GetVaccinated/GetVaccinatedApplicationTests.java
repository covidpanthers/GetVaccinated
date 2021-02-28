package com.sweng894.GetVaccinated;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
public final class GetVaccinatedApplicationTests {
  @Autowired
  private MockMvc mockMvc;

	@Test()
  void applicationStarts() {
    assertDoesNotThrow(() -> GetVaccinatedApplication.main(new String[] {"--server.port=0"}));
  }

  @Test
  void getHomePage() throws Exception {
    mockMvc.perform(get("/")).andExpect(status().isOk());
  }

}
