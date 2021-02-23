package com.sweng894.GetVaccinated;

import com.sweng894.GetVaccinated.model.VaccineLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VaccineLocationTests {
  @Test
  void checkVaccineLocation() {
    VaccineLocation vaccineLocation = new VaccineLocation(
      1,
      1,
      "Boston",
      500000
    );
    vaccineLocation.setId(2);
    vaccineLocation.setVaccineId(2);
    vaccineLocation.setLocation("Miami");
    vaccineLocation.setAvailabilityCount(10000);

    assertEquals(vaccineLocation.getId(), 2);
    assertEquals(vaccineLocation.getVaccineId(), 2);
    assertEquals(vaccineLocation.getLocation(), "Miami");
    assertEquals(vaccineLocation.getAvailabilityCount(), 10000);
  }
}
