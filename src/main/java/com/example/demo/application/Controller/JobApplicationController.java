package com.example.demo.application.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.application.DTO.CreateApplicationRequest;
import com.example.demo.application.Entity.JobApplication;
import com.example.demo.application.Service.JobApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(
            JobApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public JobApplication create(
            @RequestBody CreateApplicationRequest request,
            Authentication authentication) {

        return service.create(
                request,
                authentication.getName()
        );
    }

    @GetMapping
    public List<JobApplication> getMyApplications(
            Authentication authentication) {

        return service.getMyApplications(
                authentication.getName()
        );
    }
}