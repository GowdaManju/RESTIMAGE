package com.imageuplod.RESTIMAGE.request;

import javax.validation.constraints.Size;

public class UpdateUserRequest {
	
	@Size(min = 5)
	private String firstName;
	@Size(min = 3)
	private String lastName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
