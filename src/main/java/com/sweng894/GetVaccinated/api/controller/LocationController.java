package com.sweng894.GetVaccinated.api.controller;

import com.sweng894.GetVaccinated.api.entity.Location;
import com.sweng894.GetVaccinated.api.library.GeocodeAPI;
import com.sweng894.GetVaccinated.api.library.Geohash;
import com.sweng894.GetVaccinated.api.repository.LocationRepository;
import com.sweng894.GetVaccinated.api.services.LocationService;
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

  @Autowired private LocationService locationService;

  @PostMapping("/location")
  public Location addLocation(@RequestBody Location location) {
    LocationService locationService = new LocationService();
    return locationService.addLocation(location, locationRepository);
  }

  @GetMapping("/location/{address}")
  public Location getLocation(@PathVariable("address") String address) {
    locationService = new LocationService();
    return locationService.findLocationById(address, locationRepository);
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

  @DeleteMapping("/location/{address}")
  public String deleteLocation(@PathVariable("address") String address) {
    GeocodeAPI api = new GeocodeAPI();
    double[] latLong = api.getLatLong(address);
    return locationRepository.delete(Geohash.encode(latLong[0], latLong[1]));
  }

}
