package com.example.demo.application.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ForbiddenException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.application.DTO.ApplicationFilterRequest;
import com.example.demo.application.DTO.CreateApplicationRequest;
import com.example.demo.application.DTO.JobApplicationResponse;
import com.example.demo.application.DTO.UpdateApplicationStatusRequest;
import com.example.demo.application.Entity.ApplicationStatus;
import com.example.demo.application.Entity.JobApplication;
import com.example.demo.application.Repository.JobApplicationRepository;
import com.example.demo.application.mapper.JobApplicationMapper;
import com.example.demo.company.Entity.Company;
import com.example.demo.company.Repository.CompanyRepository;
import com.example.demo.user.Entity.User;
import com.example.demo.user.Repository.UserRepository;
@Service
public class JobApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public JobApplicationService(
            JobApplicationRepository applicationRepository,
            UserRepository userRepository,
            CompanyRepository companyRepository) {

        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public JobApplicationResponse create(
            CreateApplicationRequest request,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Company company = companyRepository.findById(request.companyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company not found"));

        JobApplication application = new JobApplication();
        application.setPosition(request.position);
        application.setSalary(request.salary);
        application.setNotes(request.notes);
        application.setApplicationDate(LocalDate.now());
        application.setStatus(ApplicationStatus.APPLIED);
        application.setUser(user);
        application.setCompany(company);

        return toResponse(applicationRepository.save(application));
    }

    public Page<JobApplicationResponse> getApplications(
            ApplicationFilterRequest filter,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Sort sort = filter.getDirection().equalsIgnoreCase("ASC")
                ? Sort.by(filter.getSortBy()).ascending()
                : Sort.by(filter.getSortBy()).descending();

        Pageable pageable = PageRequest.of(
                filter.getPage(),
                filter.getSize(),
                sort);

        Page<JobApplication> result;

        if (filter.getStatus() != null && filter.getSearch() != null) {

            result = applicationRepository
                    .findByUserIdAndStatusAndPositionContainingIgnoreCase(
                            user.getId(),
                            filter.getStatus(),
                            filter.getSearch(),
                            pageable);

        } else if (filter.getStatus() != null) {

            result = applicationRepository
                    .findByUserIdAndStatus(
                            user.getId(),
                            filter.getStatus(),
                            pageable);

        } else if (filter.getSearch() != null) {

            result = applicationRepository
                    .findByUserIdAndPositionContainingIgnoreCase(
                            user.getId(),
                            filter.getSearch(),
                            pageable);

        } else {

            result = applicationRepository
                    .findByUserId(user.getId(), pageable);
        }

        return result.map(this::toResponse);
    }

    public JobApplicationResponse updateStatus(
            Long id,
            UpdateApplicationStatusRequest request,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        JobApplication app = applicationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found"));

        if (!app.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException("Not allowed");
        }

        app.setStatus(request.getStatus());

        return toResponse(applicationRepository.save(app));
    }

    private JobApplicationResponse toResponse(JobApplication application) {

        JobApplicationResponse response = new JobApplicationResponse();
        response.setId(application.getId());
        response.setCompanyName(application.getCompany().getName());
        response.setPosition(application.getPosition());
        response.setStatus(application.getStatus());
        response.setSalary(application.getSalary());
        response.setApplicationDate(application.getApplicationDate());
        response.setNotes(application.getNotes());

        return response;
    }
}