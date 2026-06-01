package com.example.demo.application.DTO;

import com.example.demo.application.Entity.ApplicationStatus;

public class ApplicationFilterRequest {
    public int page = 0;

    public int size = 10;

    public ApplicationStatus status;

    public String search;

    public String sortBy = "createdAt";

    public String direction = "DESC";
}
