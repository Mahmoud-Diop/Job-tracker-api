package com.example.demo.application.mapper;

import com.example.demo.application.DTO.JobApplicationResponse;
import com.example.demo.application.Entity.JobApplication;

public class JobApplicationMapper {

    public static JobApplicationResponse toResponse(
            JobApplication application) {

        JobApplicationResponse response = new JobApplicationResponse();

        response.setId(application.getId());

        response.setCompanyName(
                application.getCompany().getName());

        response.setPosition(
                application.getPosition());

        response.setStatus(
                application.getStatus());

        response.setSalary(
                application.getSalary());

        response.setApplicationDate(
                application.getApplicationDate());

        response.setNotes(
                application.getNotes());

        return response;
    }
}