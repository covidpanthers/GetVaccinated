package com.sweng894.GetVaccinated.api;

import com.sweng894.GetVaccinated.api.controller.LocationController;
import com.sweng894.GetVaccinated.api.entity.Appointment;
import com.sweng894.GetVaccinated.api.entity.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Tag("integration")
@SpringBootTest
public class LocationControllerTest {
  @Autowired
  private LocationController locationController;

  @Test
  public void testAddLocationSuccess() {
    Location expected = getGenericLocation();

    locationController.addLocation(expected);
    String expectedLocation = expected.getGeohash();
    String actualLocation = locationController.getLocation(expected.getAddress()).getGeohash();
    Assertions.assertEquals(expectedLocation, actualLocation);
  }

  @Test
  public void testDeleteLocationSuccess() {
    Location expected = getGenericLocation();
    locationController.addLocation(expected);

    Assertions.assertEquals("Location Deleted!",
      locationController.deleteLocation(expected.getAddress()));
  }


  private Location getGenericLocation() {
    Location output = new Location("HDP#PA#16259",
      "dppmgnzn",
      "LOCATION",
      "Giant Eagle Pharmacy-32",
      "4810 Old William Penn Highway",
      "",
      "Export",
      "PA",
      "15632",
      "Westmoreland",
      "",
      "https://www.gianteagle.com/covidvaccine",
      40.26063,
      -80.26034
    );

    return output;
  }
}
