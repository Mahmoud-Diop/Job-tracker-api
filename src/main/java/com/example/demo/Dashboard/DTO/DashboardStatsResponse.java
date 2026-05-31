package com.example.demo.Dashboard.DTO;

public class DashboardStatsResponse {

    private long totalApplications;

    private long applied;

    private long screening;

    private long interview;

    private long technicalTest;

    private long offer;

    private long rejected;

    public DashboardStatsResponse() {
    }

    public DashboardStatsResponse(
            long totalApplications,
            long applied,
            long screening,
            long interview,
            long technicalTest,
            long offer,
            long rejected) {
        this.totalApplications = totalApplications;
        this.applied = applied;
        this.screening = screening;
        this.interview = interview;
        this.technicalTest = technicalTest;
        this.offer = offer;
        this.rejected = rejected;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public long getApplied() {
        return applied;
    }

    public long getScreening() {
        return screening;
    }

    public long getInterview() {
        return interview;
    }

    public long getTechnicalTest() {
        return technicalTest;
    }

    public long getOffer() {
        return offer;
    }

    public long getRejected() {
        return rejected;
    }

    public void setTotalApplications(long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public void setApplied(long applied) {
        this.applied = applied;
    }

    public void setScreening(long screening) {
        this.screening = screening;
    }

    public void setInterview(long interview) {
        this.interview = interview;
    }

    public void setTechnicalTest(long technicalTest) {
        this.technicalTest = technicalTest;
    }

    public void setOffer(long offer) {
        this.offer = offer;
    }

    public void setRejected(long rejected) {
        this.rejected = rejected;
    }

}
