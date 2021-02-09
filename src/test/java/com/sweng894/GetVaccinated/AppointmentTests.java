package com.sweng894.GetVaccinated;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppointmentTests {

	private Appointment appointment;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		appointment = new Appointment();
		appointment.setConfirmationNumber(123);
		appointment.setDate(java.sql.Date.valueOf("2021-02-06"));
	}

	@Test
	void testModifyAppointmentDetails() {

		String beforeName = appointment.getName();
		appointment.setName("changed name");

		String afterName = appointment.getName();

		assertNotEquals(beforeName, afterName);
	}

	@Test
	void testChangeAppointmentDate() {

		Date oldDate = appointment.getDate();
		Integer oldConfirmationNumber = appointment.getConfirmationNumber();

		appointment.setDate(java.sql.Date.valueOf("2021-03-06"));
		appointment.setConfirmationNumber(456);

		assertNotEquals(oldDate, appointment.getDate());
		assertNotEquals(oldConfirmationNumber, appointment.getConfirmationNumber());
	}

	@Test
	void testCancelAppointment() {

		appointment.setStatus("CANCELED");

		assertEquals("CANCELED", appointment.getStatus());
	}

}
