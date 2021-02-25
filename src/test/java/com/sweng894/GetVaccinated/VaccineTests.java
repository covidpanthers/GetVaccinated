package com.sweng894.GetVaccinated;

import com.sweng894.GetVaccinated.api.entity.Vaccine;
import com.sweng894.GetVaccinated.model.VaccineLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class VaccineTests {

  private Vaccine vaccine;
  private ArrayList<VaccineLocation> vaccineLocations;

  @BeforeEach
  void setUp() throws Exception {
    vaccine = new Vaccine(
      1,
      1,
      "Pfizer",
      java.sql.Date.valueOf("2021-02-10")
    );
    vaccine.setDistributionProcess(
      "This is test process"
    );

    vaccineLocations = new ArrayList<>();
    vaccineLocations.add(new VaccineLocation(
      1,
      vaccine.getId(),
      "Boston",
      100
    ));
  }

  @Test
  void checkVaccine() {
    Vaccine anotherVaccine = new Vaccine();
    anotherVaccine.setId(2);
    anotherVaccine.setCompanyId(1);
    anotherVaccine.setTitle("Zola");
    anotherVaccine.setLaunchDate(java.sql.Date.valueOf("2021-02-10"));
    anotherVaccine.setNoOfShots(5);
    anotherVaccine.setDistributionProcess("This is test process");

    assertEquals(anotherVaccine.getId(), 2);
    assertEquals(anotherVaccine.getCompanyId(), 1);
    assertEquals(anotherVaccine.getTitle(), "Zola");
    assertEquals(anotherVaccine.getLaunchDate().toString(), java.sql.Date.valueOf("2021-02-10").toString());
    assertEquals(anotherVaccine.getNoOfShots(), 5);
    assertEquals(anotherVaccine.getDistributionProcess(), "This is test process");
  }

  @Test
  void findVaccines() {
    String location = "Boston";
    boolean isFound = false;

    Iterator<VaccineLocation> it = vaccineLocations.iterator();
    while (it.hasNext()) {
      VaccineLocation vl = it.next();
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
    Iterator<VaccineLocation> it = vaccineLocations.iterator();
    while (it.hasNext()) {
      VaccineLocation vl = it.next();
      if(vl.getLocation().equals(location) && vl.getVaccineId() == vaccineId && vl.getAvailabilityCount() > 0) {
        isAvailable = true;
      }
    }
    assertTrue(isAvailable);
  }

  @Test
  void getVaccines() {
    ArrayList<Vaccine> vaccines = new ArrayList<>();
    vaccines.add(vaccine);
    assertEquals(vaccines.size(), 1);
  }

  @Test
  void getVaccineInfoById() {
    int vaccineId = 1;

    String info = null;
    ArrayList<Vaccine> vaccines = new ArrayList<>();
    vaccines.add(vaccine);
    Iterator<Vaccine> it = vaccines.iterator();
    while (it.hasNext()) {
      Vaccine v = it.next();
      if(v.getId() == vaccineId) {
        info = v.getDistributionProcess();
      }
    }
    assertEquals(info, "This is test process");
  }
}
