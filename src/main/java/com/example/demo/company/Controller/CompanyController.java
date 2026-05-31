package com.example.demo.company.Controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.company.DTO.CreateCompanyRequest;
import com.example.demo.company.Entity.Company;
import com.example.demo.company.Services.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public Company createCompany(@RequestBody CreateCompanyRequest request,
                                 Authentication authentication) {

        String email = authentication.getName();

        return companyService.createCompany(request, email);
    }

    @GetMapping
    public List<Company> getMyCompanies(Authentication authentication) {

        String email = authentication.getName();

        return companyService.getMyCompanies(email);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
}
