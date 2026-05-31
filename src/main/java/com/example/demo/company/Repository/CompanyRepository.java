package com.example.demo.company.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.company.Entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByUserId(Long userId);
}