package com.sweng894.GetVaccinated.api;

import com.sweng894.GetVaccinated.api.library.Geohash;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeohashTests {

  @Test
  public void testEncodeLatLongSuccess() {
    String expected = "dqcjqcpe";
    double latitude = 38.897701;
    double longitude = -77.036537;

    Assertions.assertEquals(expected, Geohash.encode(latitude, longitude));
    Assertions.assertNotEquals("aaabbbcc", Geohash.encode(latitude, longitude));
  }
}
