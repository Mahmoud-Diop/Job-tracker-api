package com.example.demo.company.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ForbiddenException;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.company.DTO.CompanyResponse;
import com.example.demo.company.DTO.CreateCompanyRequest;
import com.example.demo.company.DTO.UpdateCompanyRequest;
import com.example.demo.company.Entity.Company;
import com.example.demo.company.Repository.CompanyRepository;
import com.example.demo.company.mapper.CompanyMapper;
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

    public CompanyResponse createCompany(CreateCompanyRequest request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found"));

        Company company = new Company();
        company.setName(request.name);
        company.setWebsite(request.website);
        company.setLocation(request.location);
        company.setIndustry(request.industry);
        company.setUser(user);

        Company saved = companyRepository.save(company);

        return CompanyMapper.toResponse(saved);
    }

    public List<CompanyResponse> getMyCompanies(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found"));
        return companyRepository
                .findByUserId(user.getId())
                .stream()
                .map(CompanyMapper::toResponse)
                .toList();
    }

    public CompanyResponse updateCompany(
            Long companyId,
            UpdateCompanyRequest request,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found"));

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Company not found"));

        if (!company.getUser().getId()
                .equals(user.getId())) {

            throw new ForbiddenException(
                    "You cannot update this company");
        }

        company.setName(request.name);
        company.setWebsite(request.website);
        company.setLocation(request.location);
        company.setIndustry(request.industry);

        Company updated = companyRepository.save(company);

        return CompanyMapper.toResponse(updated);
    }



    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}