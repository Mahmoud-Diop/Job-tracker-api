package com.example.demo.Dashboard.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dashboard.DTO.DashboardStatsResponse;
import com.example.demo.Dashboard.Services.DashboardService;


@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public DashboardStatsResponse stats(
            Authentication authentication) {

        return dashboardService.getStats(
                authentication.getName()
        );
    }
}