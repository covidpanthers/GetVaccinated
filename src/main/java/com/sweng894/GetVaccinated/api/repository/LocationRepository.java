package com.sweng894.GetVaccinated.api.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.sweng894.GetVaccinated.api.entity.Location;
import com.sweng894.GetVaccinated.api.library.GeocodeAPI;
import com.sweng894.GetVaccinated.api.library.Geohash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LocationRepository {
  @Autowired
  public DynamoDBMapper dynamoDBMapper;

  public Location save(Location location) {
    dynamoDBMapper.save(location);
    return location;
  }

  public String delete(String geohash) {
    Location location = dynamoDBMapper.load(Location.class, "LOC#", "GEO#" + geohash);
    dynamoDBMapper.delete(location);
    return "Location Deleted!";
  }

  public Location getLocation(String address) {
    GeocodeAPI api = new GeocodeAPI();
    double[] latlong = new double[2];
    latlong = api.getLatLong(URLDecoder.decode(address, StandardCharsets.UTF_8));
    String geohash = Geohash.encode(latlong[0], latlong[1]);
    return dynamoDBMapper.load(Location.class, "LOC#", "GEO#" + geohash);
  }

  public List<Location> getLocationByState(String address) {
    return null;
  }

  public List<Location> getLocationsByGeohash(String geohash) {
    Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
    eav.put(":pk", new AttributeValue().withS("LOC#"));
    eav.put(":sk", new AttributeValue().withS("GEO#" + geohash));


    DynamoDBQueryExpression<Location> queryExpression = new DynamoDBQueryExpression<Location>()
      .withKeyConditionExpression("partitionKey = :pk and begins_with(sortKey, :sk)")
      .withExpressionAttributeValues(eav);

    return dynamoDBMapper.query(Location.class, queryExpression);

  }

  public List<Location> getAllLocations() {
    Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
    eav.put(":pk", new AttributeValue().withS("LOC#"));
    eav.put(":sk", new AttributeValue().withS("GEO#"));


    DynamoDBQueryExpression<Location> queryExpression = new DynamoDBQueryExpression<Location>()
      .withKeyConditionExpression("partitionKey = :pk and begins_with(sortKey, :sk)")
      .withExpressionAttributeValues(eav);

    List<Location> output = dynamoDBMapper.query(Location.class, queryExpression);
    return output;
  }

}
