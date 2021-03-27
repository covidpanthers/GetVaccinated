package com.sweng894.GetVaccinated.api;

import com.sweng894.GetVaccinated.api.library.Geohash;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

public class GeohashTests {

  @Test
  public void testEncodeLatLongSuccess() {
    String expected = "dqcjqcpe";
    double latitude = 38.897701;
    double longitude = -77.036537;

    Assertions.assertEquals(expected, Geohash.encode(latitude, longitude));
    Assertions.assertNotEquals("aaabbbcc", Geohash.encode(latitude, longitude));
  }

  @Test
  public void testBoundsSuccess() {
    String geohash = "dqcjqcpe";

    double expectedSwLat = 38.89760971069336;
    double expectedSwLon = -77.03681945800781;
    double expectedNeLat = 38.89778137207031;
    double expectedNeLon = -77.0364761352539;

    Map<String, Double[]> actual = Geohash.bounds(geohash);
    Double[] latLongSW = new Double[2];
    Double[] latLongNE = new Double[2];

    latLongSW = actual.get("sw");
    latLongNE = actual.get("ne");

    Assertions.assertEquals(expectedSwLat, latLongSW[0]);
    Assertions.assertEquals(expectedSwLon, latLongSW[1]);
    Assertions.assertEquals(expectedNeLat, latLongNE[0]);
    Assertions.assertEquals(expectedNeLon, latLongNE[1]);
  }

  @Test
  public void testAdjacentSuccess() {

    String expectedNorth = "dqcjqcps";
    String actualNorth = Geohash.adjacent("dqcjqcpe", "n");

    String expectedNortheast = "dqcjqcpu";
    String actualNortheast = Geohash.adjacent(Geohash.adjacent("dqcjqcpe", "n"), "e");

    String expectedEast = "dqcjqcpg";
    String actualEast = Geohash.adjacent("dqcjqcpe", "e");

    String expectedSoutheast = "dqcjqcpf";
    String actualSoutheast = Geohash.adjacent(Geohash.adjacent("dqcjqcpe", "s"), "e");

    String expectedSouth = "dqcjqcpd";
    String actualSouth = Geohash.adjacent("dqcjqcpe", "s");

    String expectedSouthwest = "dqcjqcp6";
    String actualSouthwest = Geohash.adjacent(Geohash.adjacent("dqcjqcpe", "s"), "w");

    String expectedWest = "dqcjqcp7";
    String actualWest = Geohash.adjacent("dqcjqcpe", "w");

    String expectedNorthwest = "dqcjqcpk";
    String actualNorthwest = Geohash.adjacent(Geohash.adjacent("dqcjqcpe", "n"), "w");

    Assertions.assertEquals(expectedNorth, actualNorth);
    Assertions.assertEquals(expectedNortheast, actualNortheast);
    Assertions.assertEquals(expectedEast, actualEast);
    Assertions.assertEquals(expectedSoutheast, actualSoutheast);
    Assertions.assertEquals(expectedSouth, actualSouth);
    Assertions.assertEquals(expectedSouthwest, actualSouthwest);
    Assertions.assertEquals(expectedWest, actualWest);
    Assertions.assertEquals(expectedNorthwest, actualNorthwest);
  }

  @Test
  public void testNeighborsSuccess() {
    String expectedNorth = "dqcjqcps";
    String expectedNortheast = "dqcjqcpu";
    String expectedEast = "dqcjqcpg";
    String expectedSoutheast = "dqcjqcpf";
    String expectedSouth = "dqcjqcpd";
    String expectedSouthwest = "dqcjqcp6";
    String expectedWest = "dqcjqcp7";
    String expectedNorthwest = "dqcjqcpk";

    Map<String, String> actual = Geohash.neighbors("dqcjqcpe");

    Assertions.assertEquals(expectedNorth, actual.get("n"));
    Assertions.assertEquals(expectedNortheast, actual.get("ne"));
    Assertions.assertEquals(expectedEast, actual.get("e"));
    Assertions.assertEquals(expectedSoutheast, actual.get("se"));
    Assertions.assertEquals(expectedSouth, actual.get("s"));
    Assertions.assertEquals(expectedSouthwest, actual.get("sw"));
    Assertions.assertEquals(expectedWest, actual.get("w"));
    Assertions.assertEquals(expectedNorthwest, actual.get("nw"));
  }

  @Test
  public void testDecodeSuccess() {
    double expectedLatitude = 38.897696;
    double expectedLongitude = -77.036648;

    Map<String, Double> actual = Geohash.decode("dqcjqcpe");

    Assertions.assertEquals(expectedLatitude, actual.get("latitude"));
    Assertions.assertEquals(expectedLongitude, actual.get("longitude"));
  }
}
