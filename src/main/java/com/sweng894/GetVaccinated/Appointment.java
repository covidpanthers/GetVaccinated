package com.sweng894.GetVaccinated;

class Appointment {

	private String name;
	private Integer confirmationNumber;
	private String status;
	
	public Appointment() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	public Integer getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(Integer confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
