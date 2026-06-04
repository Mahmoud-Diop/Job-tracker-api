package com.example.demo.application.Controller;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo.application.DTO.ApplicationFilterRequest;
import com.example.demo.application.DTO.CreateApplicationRequest;
import com.example.demo.application.DTO.JobApplicationResponse;
import com.example.demo.application.DTO.UpdateApplicationStatusRequest;
import com.example.demo.application.Entity.JobApplication;
import com.example.demo.application.Service.JobApplicationService;

import java.util.List;
@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public JobApplicationResponse create(
            @RequestBody CreateApplicationRequest request,
            Authentication authentication) {

        return service.create(
                request,
                authentication.getName());
    }

    @GetMapping
    public Page<JobApplicationResponse> getApplications(
            @ModelAttribute ApplicationFilterRequest filter,
            Authentication authentication) {

        return service.getApplications(
                filter,
                authentication.getName());
    }

    @PatchMapping("/{id}/status")
    public JobApplicationResponse updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateApplicationStatusRequest request,
            Authentication authentication) {

        return service.updateStatus(
                id,
                request,
                authentication.getName());
    }
}