package com.sweng894.GetVaccinated.api.library;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class GeocodeAPI {

  private HttpClient client = HttpClient.newHttpClient();
  private HttpRequest request= null;
  private final String accessKey = "8c9f5046580343cae7ff4a689594fd72";
  private String direction;

  public double[] getLatLong(String address) {
    double[] output = new double[2];
    request = HttpRequest.newBuilder(URI.create("http://api.positionstack.com/v1/forward" +
      "?access_key=" + accessKey + "&query=" + URLEncoder.encode(address, StandardCharsets.UTF_8)))
      .header("Accept", "application/json")
      .build();

    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);


      JSONObject json = new JSONObject((Map<String, ?>)parser.parse(response.body()));
      JSONArray array = (JSONArray) json.get("data");
      JSONObject result = (JSONObject) array.get(0);

      output[0] = (double) result.get("latitude");
      output[1] = (double) result.get("longitude");
      return output;

    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return output;
  }

  public String getAddress(Double latitude, Double longitude) {
    String output;
    String query = "&query=" + latitude.toString() + "," + longitude.toString();

    request = HttpRequest.newBuilder(URI.create("http://api.positionstack.com/v1/reverse" +
      "?access_key=" + accessKey + query))
      .header("Accept", "application/json")
      .build();

    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);


      JSONObject json = new JSONObject((Map<String, ?>) parser.parse(response.body()));
      JSONArray array = (JSONArray) json.get("data");
      JSONObject result = (JSONObject) array.get(0);

      output = result.get("name").toString();

      return output;

    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

  public double[] getLatLongFromZip(String zip) {
    double[] coordinates = new double[2];

    request = HttpRequest.newBuilder(URI.create("http://api.positionstack.com/v1/forward" +
      "?access_key=" + accessKey + "&query=" + zip))
      .header("Accept", "application/json")
      .build();

    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);


      JSONObject json = new JSONObject((Map<String, ?>) parser.parse(response.body()));
      JSONArray array = (JSONArray) json.get("data");
//      JSONObject result = (JSONObject) array.get(0);

      for (Object element: array) {
        JSONObject jsonObject = (JSONObject) element;
        System.out.println(jsonObject);
        if (jsonObject.containsKey("type") && jsonObject.get("type").equals("postalcode")) {
          coordinates[0] = (double) jsonObject.get("latitude");
          coordinates[1] = (double) jsonObject.get("longitude");
          return coordinates;
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  public double distanceBetweenTwoPoints(double lat1, double lat2, double lon1, double lon2) {
    //https://rosettacode.org/wiki/Haversine_formula

    final double R = 3958.8;
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);
    lat1 = Math.toRadians(lat1);
    lat2 = Math.toRadians(lat2);

    double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
    double c = 2 * Math.asin(Math.sqrt(a));
    return R * c;
  }

}
