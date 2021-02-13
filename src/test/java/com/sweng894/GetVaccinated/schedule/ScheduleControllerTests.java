package com.sweng894.GetVaccinated.schedule;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public final class ScheduleControllerTests {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getSchedule() throws Exception {
    this.mockMvc.perform(get("/schedule")).andExpect(status().isOk());
  }

  @Test
  public void postSchedule() throws Exception {
    this.mockMvc.perform(post("/schedule")).andExpect(status().isOk());
  }
}
