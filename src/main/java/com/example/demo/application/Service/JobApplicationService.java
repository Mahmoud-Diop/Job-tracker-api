package com.example.demo.application.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ForbiddenException;
import com.example.demo.Exceptions.ResourceNotFoundException;
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
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "User not found"));

                Company company = companyRepository.findById(
                                request.companyId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Company not found"));

                JobApplication application = new JobApplication();

                application.setPosition(request.position);

                application.setSalary(request.salary);

                application.setNotes(request.notes);

                application.setApplicationDate(LocalDate.now());

                application.setStatus(ApplicationStatus.APPLIED);

                application.setUser(user);

                application.setCompany(company);

                JobApplication savedApplication = applicationRepository.save(application);
                return convertToResponse(savedApplication);
        }

        public List<JobApplicationResponse> getMyApplications(
                        String email) {

                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "User not found"));

                return applicationRepository.findByUserId(user.getId()).stream()
                                .map(this::convertToResponse)
                                .collect(java.util.stream.Collectors.toList());
        }

        public JobApplicationResponse updateStatus(
                        Long applicationId,
                        UpdateApplicationStatusRequest request,
                        String email) {

                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "User not found"));

                JobApplication application = applicationRepository.findById(applicationId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Application not found"));

                if (!application.getUser().getId()
                                .equals(user.getId())) {

                        throw new ForbiddenException(
                                        "You cannot modify this application");
                }

                application.setStatus(
                                request.getStatus());

                JobApplication updated = applicationRepository.save(application);

                return JobApplicationMapper.toResponse(updated);
        }

        private JobApplicationResponse convertToResponse(JobApplication application) {
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
