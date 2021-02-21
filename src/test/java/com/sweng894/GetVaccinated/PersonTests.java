package com.sweng894.GetVaccinated;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTests {

	private Person person;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		person = new Person("John Doe", "2", "N");
	}

	@Test
	void testVaccinatedPerson() {

		person.setIsVaccinated("Y");

		assertTrue("Y".equals(person.getIsVaccinated()),
				person.getName() + " is eligible, has not been vaccinated yet.");
	}

	@Test
	void testNotVaccinatedPerson() {
		assertTrue("N".equals(person.getIsVaccinated()), person.getName() + " is eligible, has not been vaccinated.");
	}

	@Test
	void testEligiblePhase() {
		boolean inEligiblePhase = person.inEligiblePhase(person);

		assertFalse(inEligiblePhase, person.getName() + " is not in an eligible phase.");
	}

	@Test
	void testIneligiblePhase() {

		Person person = new Person();
		person.setName("Jane Doe");
		person.setIsVaccinated("Y");
		person.setPhase("1");

		boolean inEligiblePhase = person.inEligiblePhase(person);

		assertTrue(inEligiblePhase, person.getName() + " is in an eligible phase.");
	}
}
