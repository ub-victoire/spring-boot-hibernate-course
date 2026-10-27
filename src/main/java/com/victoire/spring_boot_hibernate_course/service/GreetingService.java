package com.victoire.spring_boot_hibernate_course.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// marks the class as a service component.
@Service
public class GreetingService {

	@Value ("${app.greeting}")
	public String getGreeting(){
		return "Hello! Spring dependency injection works.";
	}
}
