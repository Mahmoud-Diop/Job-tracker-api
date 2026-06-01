package com.example.demo.Dashboard.Services;

import org.springframework.stereotype.Service;

import com.example.demo.Dashboard.DTO.DashboardStatsResponse;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.application.Entity.ApplicationStatus;
import com.example.demo.application.Repository.JobApplicationRepository;
import com.example.demo.user.Entity.User;
import com.example.demo.user.Repository.UserRepository;

@Service
public class DashboardService {

    private final UserRepository userRepository;

    private final JobApplicationRepository applicationRepository;

    public DashboardService(
            UserRepository userRepository,
            JobApplicationRepository applicationRepository) {

        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
    }

    public DashboardStatsResponse getStats(
            String email) {

        User user = userRepository
        .findByEmail(email)
        .orElseThrow(() ->
                new ResourceNotFoundException(
                        "User not found"));

        Long userId = user.getId();

        DashboardStatsResponse stats =
                new DashboardStatsResponse();

        stats.setTotalApplications(
                applicationRepository.countByUserId(userId)
        );

        stats.setApplied(
                applicationRepository.countByUserIdAndStatus(
                        userId,
                        ApplicationStatus.APPLIED
                )
        );

        stats.setScreening(
                applicationRepository.countByUserIdAndStatus(
                        userId,
                        ApplicationStatus.SCREENING
                )
        );

        stats.setInterview(
                applicationRepository.countByUserIdAndStatus(
                        userId,
                        ApplicationStatus.INTERVIEW
                )
        );

        stats.setTechnicalTest(
                applicationRepository.countByUserIdAndStatus(
                        userId,
                        ApplicationStatus.TECHNICAL_TEST
                )
        );

        stats.setOffer(
                applicationRepository.countByUserIdAndStatus(

                        userId,
                        
                        ApplicationStatus.OFFER
                )
        );

        stats.setRejected(
                applicationRepository.countByUserIdAndStatus(
                        userId,
                        ApplicationStatus.REJECTED
                )
        );

        return stats;
    }
}