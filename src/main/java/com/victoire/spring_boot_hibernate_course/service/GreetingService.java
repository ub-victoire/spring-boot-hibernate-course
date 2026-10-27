package com.victoire.spring_boot_hibernate_course.service;

import org.springframework.stereotype.Service;

// marks the class as a service component.
@Service
public class GreetingService {

	public String getGreeting(){
		return "Hello! Spring dependency injection works.";
	}
}
