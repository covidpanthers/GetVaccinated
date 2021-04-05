package com.sweng894.GetVaccinated.schedule;

public class Util {
  public static String parseDate(ScheduleRequest request) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.
      append("2021-")
      .append(request.getMonth() < 9 ? "0"+request.getMonth() : request.getMonth())
      .append("-")
      .append(request.getDay() < 9 ? "0"+request.getDay() : request.getDay())
      .append(" ")
      .append(request.getTime());
    return stringBuilder.toString();
  }
}
