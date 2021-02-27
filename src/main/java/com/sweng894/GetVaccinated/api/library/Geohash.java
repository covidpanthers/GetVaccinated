package com.sweng894.GetVaccinated.api.library;

import net.minidev.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Geohash {

  private static final int precision = 8;
  private static final String base32 = "0123456789bcdefghjkmnpqrstuvwxyz";

  public static String encode (double latitude, double longitude) {
    StringBuilder geohash = new StringBuilder();

    double latMin = -90;
    double latMax = 90;
    double lonMin = -180;
    double lonMax = 180;
    boolean evenBit = true;
    int bit = 0;


    int indexBase32 = 0;

    while(geohash.length() < precision) {
      if (evenBit) {
        double lonMid = (lonMin + lonMax) / 2;
        if (longitude >= lonMid) {
          indexBase32 = indexBase32 * 2 + 1;
          lonMin = lonMid;
        } else {
          indexBase32 = indexBase32 * 2;
          lonMax = lonMid;
        }
      } else {
        double latMid = (latMin + latMax) / 2;
        if (latitude >= latMid) {
          indexBase32 = indexBase32 * 2 + 1;
          latMin = latMid;
        } else {
          indexBase32 = indexBase32 * 2;
          latMax = latMid;
        }
      }

      evenBit = !evenBit;

      if (++bit == 5) {
        geohash.append(base32.charAt(indexBase32));
        bit = 0;
        indexBase32 = 0;
      }
    }
    return geohash.toString();
  }

  public static String encode(String address) {
    String output = "";


    JSONObject jsonObject = new JSONObject();
    String query = URLEncoder.encode(address, StandardCharsets.UTF_8);


    return output;
  }
}
