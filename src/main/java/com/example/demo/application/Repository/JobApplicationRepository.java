package com.example.demo.application.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.application.Entity.ApplicationStatus;
import com.example.demo.application.Entity.JobApplication;
@Repository
public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    Page<JobApplication> findByUserId(
            Long userId,
            Pageable pageable);

    Page<JobApplication> findByUserIdAndStatus(
            Long userId,
            ApplicationStatus status,
            Pageable pageable);

    Page<JobApplication> findByUserIdAndPositionContainingIgnoreCase(
            Long userId,
            String position,
            Pageable pageable);

    Page<JobApplication> findByUserIdAndStatusAndPositionContainingIgnoreCase(
            Long userId,
            ApplicationStatus status,
            String position,
            Pageable pageable);

    long countByUserId(Long userId);

    long countByUserIdAndStatus(
            Long userId,
            ApplicationStatus status);
}