package com.sweng894.GetVaccinated.api;

import com.sweng894.GetVaccinated.api.controller.HealthDepartmentController;
import com.sweng894.GetVaccinated.api.entity.HealthDepartment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

@Tag("integration")
@SpringBootTest
public class HealthDepartmentControllerTest {
  @Autowired
  private HealthDepartmentController healthDepartmentController;

  @Test
  public void testSaveHealthDepartmentSuccess() {
    HealthDepartment expected = getGenericHealthDepartment();


    healthDepartmentController.addHealthDepartment(expected);
    var expectedState = expected.getState();
    var actualState = healthDepartmentController.getHealthDepartment("#HDP", expected.getState()).getState();
    Assertions.assertEquals(expectedState, actualState);
  }

  @Test
  public void testUpdateHealthDepartmentSuccess() {
    HealthDepartment expected = getGenericHealthDepartment();
    HealthDepartment actual = null;

    healthDepartmentController.addHealthDepartment(expected);
    String update = "1C";
    expected.setPhase(update);

    actual = healthDepartmentController.updateDepartment("#HDP", "PA", expected);

    Assertions.assertEquals(expected.getPhase(), actual.getPhase());
  }

  @Test
  public void testDeleteHealthDepartmentSuccess() {
    HealthDepartment expected = getGenericHealthDepartment();

    Assertions.assertEquals("Department Deleted!",
      healthDepartmentController.deleteHealthDepartment(expected.getId(),
        expected.getState()));
  }

  private HealthDepartment getGenericHealthDepartment() {
    HealthDepartment output = new HealthDepartment(
      "#HDP",
      "PA",
      "1B",
      "ACTIVE",
      null
    );
    List<String> description = new ArrayList<>();
    description.add("doctors");
    description.add("firefighters");
    description.add("EMTs");

    output.setDescription(description);

    return output;
  }
}
