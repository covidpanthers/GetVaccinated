package com.sweng894.GetVaccinated.api.library;

import java.net.http.HttpResponse;

public interface IGeocodeAPI {
  double[] getLatLong(String address);

  String getAddress(double latitude, double longitude);
}
