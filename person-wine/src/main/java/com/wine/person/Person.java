package com.wine.person;

public class Person {
	
	private String personId;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Person(String personId) {
		
		this.personId = personId;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + "]";
	}
	

}
