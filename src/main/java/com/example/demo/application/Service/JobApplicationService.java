package com.example.demo.application.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.application.DTO.CreateApplicationRequest;
import com.example.demo.application.Entity.ApplicationStatus;
import com.example.demo.application.Entity.JobApplication;
import com.example.demo.application.Repository.JobApplicationRepository;
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

    public JobApplication create(
            CreateApplicationRequest request,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Company company = companyRepository.findById(request.companyId)
                .orElseThrow();

        JobApplication application = new JobApplication();

        application.setPosition(request.position);

        application.setSalary(request.salary);

        application.setNotes(request.notes);

        application.setApplicationDate(LocalDate.now());

        application.setStatus(ApplicationStatus.APPLIED);

        application.setUser(user);

        application.setCompany(company);

        return applicationRepository.save(application);
    }

    public List<JobApplication> getMyApplications(
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return applicationRepository.findByUserId(user.getId());
    }
}
