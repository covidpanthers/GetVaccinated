package com.sweng894.GetVaccinated;

import com.sweng894.GetVaccinated.model.Company;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTests {

  @Test
  void checkCompany() {
    Company company = new Company();
    company.setId(1);
    company.setName("Pfizer");
    company.setAddress("ABC Street");

    assertEquals(company.getId(), 1);
    assertEquals(company.getName(), "Pfizer");
    assertEquals(company.getAddress(), "ABC Street");
  }
}
