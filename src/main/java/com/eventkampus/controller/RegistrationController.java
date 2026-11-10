
package com.eventkampus.controller;

import com.eventkampus.dto.RegistrationRequest;
import com.eventkampus.entity.Registration;
import com.eventkampus.service.RegistrationService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<Registration> register(
            @Valid @RequestBody RegistrationRequest request) {

        Registration registration = registrationService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registration);
    }
}
