package com.sweng894.GetVaccinated.api;

import com.sweng894.GetVaccinated.api.library.GeocodeAPI;
import net.bytebuddy.description.type.TypeList;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeocodeAPITests {

  @Test
  public void testGetLatLongSuccess() {
    //TODO Mock tests to reduce strain on API
    GeocodeAPI api = new GeocodeAPI();
    double[] expected = new double[2];
    expected[0] = 38.897675;
    expected[1] = -77.036547;

    double[] actual = api.getLatLong("1600 Pennsylvania Ave, Washington DC");

    Assertions.assertEquals(expected[0], actual[0]);
    Assertions.assertEquals(expected[1], actual[1]);
  }

  @Test
  public void testGetAddressSuccess() {
    //TODO Mock tests to reduce strain on API
    GeocodeAPI api = new GeocodeAPI();
    String expected = "1600 Pennsylvania Avenue NW";
    double latitude = 38.897675;
    double longitude = -77.036547;

    String actual = api.getAddress(latitude, longitude);

    Assertions.assertEquals(expected, actual);
  }
}
