package com.example.demo.application.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.application.Entity.ApplicationStatus;
import com.example.demo.application.Entity.JobApplication;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByUserId(Long userId);

    long countByUserId(Long userId);

    long countByUserIdAndStatus(
            Long userId,
            ApplicationStatus status);

}