package com.sweng894.GetVaccinated.schedule;

import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository {
  ScheduleConfirmation saveRequest(ScheduleRequest request);
}
