package com.sweng894.GetVaccinated.schedule;

public class Util {
  public static String parseDate(ScheduleRequest request) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.
      append("2021-")
      .append(request.getMonth())
      .append("-")
      .append(request.getDay())
      .append(" ")
      .append(request.getTime());
    return stringBuilder.toString();
  }
}
