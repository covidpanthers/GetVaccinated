package com.sweng894.GetVaccinated.schedule;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
public final class ScheduleControllerTests {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ScheduleRepository repository;

  @Test
  public void getSchedule() throws Exception {
    mockMvc.perform(get("/schedule")).andExpect(status().isOk());
  }

  @Test
  public void postSchedule() throws Exception {
    mockMvc.perform(post("/schedule")).andExpect(status().is3xxRedirection());
  }

  @Test
  public void getConfirmationNotFound() throws Exception {
    mockMvc.perform(get("/schedule/does-not-exist")).andExpect(status().isNotFound());
  }

  @Test
  public void getConfirmation() throws Exception {
    var request = new ScheduleRequest();
    request.setMonth(6);
    request.setDay(1);
    request.setTime("11:00");
    var confirmation = repository.saveRequest(request);
    mockMvc.perform(get("/schedule/" + confirmation.getConfirmationNumber())).andExpect(status().isOk());
  }

  @Test
  public void getCalendarInviteNotFound() throws Exception {
    mockMvc.perform(get("/schedule/confirmation/BAD_CONFIRMATION.ics")).andExpect(status().isNotFound());
  }

  @Test
  public void getCalendarInvite() throws Exception {
    var request = new ScheduleRequest();
    request.setMonth(6);
    request.setDay(1);
    request.setTime("16:00");
    var confirmation = repository.saveRequest(request);
    mockMvc.perform(get("/schedule/confirmation/" + confirmation.getConfirmationNumber() + ".ics"))
      .andExpect(status().isOk())
      .andExpect(content().contentType("text/calendar"));
  }
  
  @Test
  public void getIneligiblePage() throws Exception {
    mockMvc.perform(get("/schedule/ineligible")).andExpect(status().isOk());
  }
  
  @Test
  public void getEligiblePage() throws Exception {
    mockMvc.perform(get("/schedule/eligible")).andExpect(status().isOk());
  }
}
