package com.sweng894.GetVaccinated;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GetVaccinatedApplicationTests {
	
	private Appointment appointment;
	
    @BeforeEach                                   
    public void setUp() throws Exception {
		appointment = new Appointment();
    }

	@Test
	void contextLoads() {
	}

	@Test
	void testModifyAppointment() {
		
		String beforeName = appointment.getName();
		appointment.setName("changed name");
		
		String afterName = appointment.getName();
		
		assertNotEquals(beforeName, afterName);
	}
	
	@Test
	void testCancelAppointment() {
	 
		appointment.setStatus("CANCELED");
		
		assertEquals("CANCELED", appointment.getStatus());
	}
}
