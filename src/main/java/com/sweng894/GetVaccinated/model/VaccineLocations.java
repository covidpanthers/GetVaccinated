package com.sweng894.GetVaccinated.model;

public class VaccineLocations {
  private int id;
  private int vaccineId;
  private String location;

  public VaccineLocations(int id, int vaccineId, String location) {
    this.id = id;
    this.vaccineId = vaccineId;
    this.location = location;
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
}
