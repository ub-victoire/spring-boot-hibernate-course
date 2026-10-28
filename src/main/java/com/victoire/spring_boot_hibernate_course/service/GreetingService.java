package com.victoire.spring_boot_hibernate_course.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// marks the class as a service component.
@Service
public class GreetingService {

	//  injects the value of the `app.greeting` property into a field.
	@Value ("${app.greeting}")
	private String greeting;
	public String getGreeting(){
		return greeting;
	}
}