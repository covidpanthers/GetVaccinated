package com.sweng894.GetVaccinated.api.services;

import com.sweng894.GetVaccinated.api.entity.Location;
import com.sweng894.GetVaccinated.api.repository.LocationRepository;

import java.util.List;

public interface LocationService {

  Location addLocation(Location location);
  Location findLocationById(String id);
  List<Location> findLocationByDistance(LocationRepository locationRepository,
                                        String zip, int distance);
}
