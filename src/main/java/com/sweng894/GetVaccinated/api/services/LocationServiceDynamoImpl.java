package com.sweng894.GetVaccinated.api.services;

import com.sweng894.GetVaccinated.api.entity.Location;
import com.sweng894.GetVaccinated.api.library.GeocodeAPI;
import com.sweng894.GetVaccinated.api.library.Geohash;
import com.sweng894.GetVaccinated.api.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LocationServiceDynamoImpl implements LocationService{

  private LocationRepository locationRepository;

  @Override
  public Location findLocationById(String address) {
    return locationRepository.getLocation(address);
  }

  @Override
  public List<Location> findLocationByDistance(LocationRepository locationRepository,
                                               String zip, int distance) {
    this.locationRepository = locationRepository;
    Location origin = new Location();
    GeocodeAPI api = new GeocodeAPI();
    List<Location> output = new ArrayList<>();

    double[] coordinates = new double[2];
    coordinates = api.getLatLongFromZip(zip);

    origin.setLatitude(coordinates[0]);
    origin.setLongitude(coordinates[1]);

    origin.setGeohash(Geohash.encode(origin.getLatitude(), origin.getLongitude()));

    List<Location> locations = locationRepository.getLocationsByGeohash(origin.getGeohash().substring(0, 4));

//    Map<String, String> neighbors = Geohash.neighbors(origin.getGeohash().substring(0, 3));
//    neighbors.forEach((k, v) -> locations.addAll(locationRepository.getLocationsByGeohash(v)));

    for (Location location: locations) {
      double totalDistance = api.distanceBetweenTwoPoints(origin.getLatitude(), location.getLatitude(),
        origin.getLongitude(), location.getLongitude());
      if ( totalDistance < distance) {
        output.add(location);
      }
    }
    return output;
  }

  public List<Location> findLocationByState(String state) {
    return null;
  }

  public Location addLocation(Location location) {
    GeocodeAPI api = new GeocodeAPI();
    double[] latlong = new double[2];
    latlong = api.getLatLong(URLDecoder.decode(location.getAddress(), StandardCharsets.UTF_8));
    location.setLatitude(latlong[0]);
    location.setLongitude(latlong[1]);

    String geohash = Geohash.encode(location.getLatitude(), location.getLongitude());
    location.setGeohash(geohash);
    return locationRepository.save(location);
  }
}
