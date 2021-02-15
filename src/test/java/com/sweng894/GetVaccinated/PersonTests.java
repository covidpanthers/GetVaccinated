package com.sweng894.GetVaccinated;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
	void testEligiblePerson() {

		// Person has not been vaccinated and is not in the current rollout phase
		if (("1B".compareTo(person.getPhase())) <= 0) {
			assertTrue("N".equals(person.getIsVaccinated()), person.getName() + " is ineligible.");
		}

		// Person has been vaccinated
		Person person = new Person();
		person.setName("Jane Doe");
		person.setIsVaccinated("Y");
		person.setPhase("2");
		assertTrue("Y".equals(person.getIsVaccinated()), person.getName() + " is ineligible.");
	}

}
