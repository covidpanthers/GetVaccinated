package com.sweng894.GetVaccinated.model;

public class VaccineLocation {
  private int id;
  private int vaccineId;
  private String location;
  private int availabilityCount;

  public VaccineLocation(int id, int vaccineId, String location, int availabilityCount) {
    this.id = id;
    this.vaccineId = vaccineId;
    this.location = location;
    this.availabilityCount = availabilityCount;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getVaccineId() {
    return vaccineId;
  }

  public void setVaccineId(int vaccineId) {
    this.vaccineId = vaccineId;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getAvailabilityCount() {
    return availabilityCount;
  }

  public void setAvailabilityCount(int availabilityCount) {
    this.availabilityCount = availabilityCount;
  }
}
