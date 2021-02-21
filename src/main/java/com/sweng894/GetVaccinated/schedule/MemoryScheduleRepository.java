package com.sweng894.GetVaccinated.schedule;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A repository that stores state in-memory.
 */
public final class MemoryScheduleRepository implements ScheduleRepository {
  private static final ConcurrentHashMap<String, ScheduleRequest> database = new ConcurrentHashMap<>();
  public ScheduleConfirmation saveRequest(ScheduleRequest request) {
    var confirmationNumber = RandomStringUtils.randomAlphanumeric(16).toUpperCase(Locale.ROOT);
    var confirmation = new ScheduleConfirmation();
    confirmation.setConfirmationNumber(confirmationNumber);
    database.put(confirmationNumber, request);
    return confirmation;
  }
  public ScheduleRequest getRequest(String confirmationNumber) {
    return database.get(confirmationNumber);
  }
}
