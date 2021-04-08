package com.sweng894.GetVaccinated.api.controller;

import com.sweng894.GetVaccinated.api.entity.Location;
import com.sweng894.GetVaccinated.api.library.GeocodeAPI;
import com.sweng894.GetVaccinated.api.library.Geohash;
import com.sweng894.GetVaccinated.api.repository.LocationRepository;
import com.sweng894.GetVaccinated.api.services.LocationService;
import com.sweng894.GetVaccinated.api.services.LocationServiceDynamoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class LocationController {
  @Autowired
  private LocationRepository locationRepository;

  @Autowired private LocationServiceDynamoImpl locationService;

  @PostMapping("/location")
  public Location addLocation(@RequestBody Location location) {
    LocationService locationService = new LocationServiceDynamoImpl();
    return locationService.addLocation(location);
  }

  @GetMapping("/location/{address}")
  public Location getLocation(@PathVariable("address") String address) {
    Location location = locationRepository.getLocation(address);
    return location;
  }

  @GetMapping("/location/{zip}/{distance}")
  public List<Location> getLocationsByDistance(@PathVariable("zip") String zip,
    @PathVariable("distance") int distance) {
    return locationService.findLocationByDistance(locationRepository, zip, distance);
  }

  @GetMapping("/location/all")
  public List<Location> getAllLocations() {
    return locationRepository.getAllLocations();
  }
}
