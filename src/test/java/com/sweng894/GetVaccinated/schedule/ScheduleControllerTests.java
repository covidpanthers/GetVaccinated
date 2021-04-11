package com.sweng894.GetVaccinated.schedule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sweng894.GetVaccinated.api.entity.Appointment;
import com.sweng894.GetVaccinated.api.repository.AppointmentRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
public final class ScheduleControllerTests {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  AppointmentRepository appointmentRepository;

  @Test
  public void getSchedule() throws Exception {
    mockMvc.perform(get("/schedule")).andExpect(status().isOk());
  }

  @Test
  public void postSchedule() throws Exception {
    ScheduleRequest request = new ScheduleRequest();
    request.setEmail("test@test.com");

    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(request);

    mockMvc.perform(
      post("/schedule")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestJson)
    )
      .andExpect(status().is3xxRedirection());
  }

  @Test
  public void getConfirmationNotFound() throws Exception {
    mockMvc.perform(get("/schedule/does-not-exist")).andExpect(status().isNotFound());
  }

  @Test
  public void getConfirmation() throws Exception {
    Appointment a = new Appointment();
    a.setConfirmationNumber("12345");
    when(appointmentRepository.getAppointmentByConfirmationNumber(anyString())).thenReturn(a);

    mockMvc.perform(get("/schedule/" + a.getConfirmationNumber())).andExpect(status().isOk());
  }

  @Test
  public void getCalendarInviteNotFound() throws Exception {
    mockMvc.perform(get("/schedule/confirmation/BAD_CONFIRMATION.ics")).andExpect(status().isNotFound());
  }

  @Test
  public void getCalendarInvite() throws Exception {
    Appointment a = new Appointment();
    a.setConfirmationNumber("12345");
    a.setDate("2021-01-01 11:00");
    when(appointmentRepository.save(anyObject())).thenReturn(a);
    when(appointmentRepository.getAppointmentByConfirmationNumber(anyString())).thenReturn(a);

    mockMvc.perform(get("/schedule/confirmation/" + a.getConfirmationNumber() + ".ics"))
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
