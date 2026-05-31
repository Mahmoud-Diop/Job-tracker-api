package com.example.demo.company.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.company.DTO.CreateCompanyRequest;
import com.example.demo.company.Entity.Company;
import com.example.demo.company.Repository.CompanyRepository;
import com.example.demo.user.Entity.User;
import com.example.demo.user.Repository.UserRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository,
                          UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public Company createCompany(CreateCompanyRequest request, String email) {

       User user = userRepository.findByEmail(email)
        .orElseThrow(() ->
                new ResourceNotFoundException(
                        "User not found"));

        Company company = new Company();
        company.setName(request.name);
        company.setWebsite(request.website);
        company.setLocation(request.location);
        company.setIndustry(request.industry);
        company.setUser(user);

        return companyRepository.save(company);
    }

    public List<Company> getMyCompanies(String email) {

        User user = userRepository.findByEmail(email)
        .orElseThrow(() ->
                new ResourceNotFoundException(
                        "User not found"));
        return companyRepository.findByUserId(user.getId());
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}