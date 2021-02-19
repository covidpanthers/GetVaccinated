package com.sweng894.GetVaccinated.schedule;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

public class WeekTests {

  @Test
  public void february() throws Exception {
    var weeks = Week.weeksOfMonth(2021, 2);
    assertEquals(weeks.get(0).get(DayOfWeek.MONDAY), 1);
    assertEquals(weeks.get(4).get(DayOfWeek.SUNDAY), 28);
    assertNull(weeks.get(4).get(DayOfWeek.MONDAY));
  }
}
