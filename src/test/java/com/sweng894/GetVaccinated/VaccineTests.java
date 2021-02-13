package com.sweng894.GetVaccinated;

import com.sweng894.GetVaccinated.model.Vaccine;
import com.sweng894.GetVaccinated.model.VaccineLocations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class VaccineTests {

  private Vaccine vaccine;
  private ArrayList vaccineLocations;

  @BeforeEach
  void setUp() throws Exception {
    vaccine = new Vaccine(
      1,
      1,
      "Pfizer",
      java.sql.Date.valueOf("2021-02-10")
    );
    vaccineLocations = new ArrayList<VaccineLocations>();
    vaccineLocations.add(new VaccineLocations(
      1,
      vaccine.getId(),
      "Boston",
      100
    ));
  }

  @Test
  void findVaccines() {
    String location = "Boston";
    boolean isFound = false;

    Iterator it = vaccineLocations.iterator();
    while (it.hasNext()) {
      VaccineLocations vl = (VaccineLocations) it.next();
      if(vl.getLocation().equals(location)) {
        isFound = true;
      }
    }
    assertTrue(isFound);
  }

  @Test
  void checkVaccineAvailability() {
    int vaccineId = 1;
    String location = "Boston";

    boolean isAvailable = false;
    Iterator it = vaccineLocations.iterator();
    while (it.hasNext()) {
      VaccineLocations vl = (VaccineLocations) it.next();
      if(vl.getLocation().equals(location) && vl.getVaccineId() == vaccineId && vl.getAvailabilityCount() > 0) {
        isAvailable = true;
      }
    }
    assertTrue(isAvailable);
  }

  @Test
  void getVaccines() {
    ArrayList vaccines = new ArrayList();
    vaccines.add(vaccine);
    assertEquals(vaccines.size(), 1);
  }
}
