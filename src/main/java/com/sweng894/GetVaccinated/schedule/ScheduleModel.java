package com.sweng894.GetVaccinated.schedule;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class ScheduleModel {
  public final LocalDate now = LocalDate.now();
  public final List<String> times = Arrays.asList(
    "10:00", "11:00", "12:00", "13:00", "14:00",
    "15:00", "16:00", "17:00"
    );
  private final ScheduleRequest _request = new ScheduleRequest();
  public ScheduleRequest getRequest() {
    return _request;
  }
  public String getTime() {
    return getRequest().getTime();
  }
  public void setTime(String time) {
    getRequest().setTime(time);
  }
  public Integer getMonth() {
    return getRequest().getMonth();
  }
  public void setMonth(Integer month) {
    getRequest().setMonth(month);
  }
  public Integer getDay() {
    return getRequest().getDay();
  }
  public void setDay(Integer day) {
    getRequest().setDay(day);
  }
  public List<Map<DayOfWeek, Integer>> weeks() {
    return Week.weeksOfMonth(2021, getMonth());
  }
  public String classappend(Map<DayOfWeek, Integer> week, DayOfWeek dayOfWeek) {
    var day = getDay();
    var dateOfMonth = week.get(dayOfWeek);
    return day != null && dateOfMonth != null && dateOfMonth.equals(day) ? "selected-date" : "";
  }
  public boolean shouldEnableLink(Map<DayOfWeek, Integer> week, DayOfWeek dayOfWeek) {
    var month = getMonth();
    var monthValue = now.getMonthValue();
    var dateOfMonth = week.get(dayOfWeek);
    if (dateOfMonth == null) return false;
    if (monthValue > month) {
      return false;
    } else if (monthValue < month) {
      return true;
    } else if (now.getDayOfMonth() < dateOfMonth) {
      return true;
    }
    return false;
  }
}
