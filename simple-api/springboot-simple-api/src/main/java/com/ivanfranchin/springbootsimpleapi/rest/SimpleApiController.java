package com.ivanfranchin.springbootsimpleapi.rest;

import com.ivanfranchin.springbootsimpleapi.domain.Greeting;
import com.ivanfranchin.springbootsimpleapi.service.GreetingService;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/greeting")
public class SimpleApiController {

    private static final Logger log = LoggerFactory.getLogger(SimpleApiController.class);

    private final GreetingService greetingService;

    public SimpleApiController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public Greeting greetName(@RequestParam(defaultValue = "World", required = false) @NotBlank String name) {
        log.info("Received request, name: {}", name);
        return greetingService.greet(name);
    }
}
