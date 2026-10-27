package com.victoire.spring_boot_hibernate_course.controller;

import com.victoire.spring_boot_hibernate_course.service.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// marks this class as a web controller that returns response data.
@RestController
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    // maps HTTP GET requests to `/hello` to the `hello()` method.
    @GetMapping("/hello")
    public String hello() {
        return greetingService.getGreeting();
    }
}
