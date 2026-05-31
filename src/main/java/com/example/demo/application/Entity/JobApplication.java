package com.example.demo.application.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.company.Entity.Company;
import com.example.demo.user.Entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private Double salary;

    private LocalDate applicationDate;

    @Column(length = 2000)
    private String notes;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public JobApplication() {
    }

    public Long getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public Double getSalary() {
        return salary;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public Company getCompany() {
        return company;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}