package com.sweng894.GetVaccinated.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleConfiguration {
  @Bean
  public ScheduleRepository scheduleRepository() {
    return new MemoryScheduleRepository();
  }
}
