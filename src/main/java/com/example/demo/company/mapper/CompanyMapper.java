package com.example.demo.company.mapper;

import com.example.demo.company.DTO.CompanyResponse;
import com.example.demo.company.Entity.Company;

public class CompanyMapper {

    public static CompanyResponse toResponse(
            Company company) {

        CompanyResponse response =
                new CompanyResponse();

        response.setId(company.getId());

        response.setName(company.getName());

        response.setWebsite(company.getWebsite());

        response.setLocation(company.getLocation());

        response.setIndustry(company.getIndustry());

        return response;
    }
}
