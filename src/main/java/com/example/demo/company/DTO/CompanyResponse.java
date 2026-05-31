package com.example.demo.company.DTO;
public class CompanyResponse {

    private Long id;

    private String name;

    private String website;

    private String location;

    private String industry;

    public CompanyResponse() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    public String getWebsite() {
        return website;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
    public String getIndustry() {
        return industry;
    }

    

}