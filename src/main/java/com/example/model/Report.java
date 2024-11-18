package com.example.model;

public class Report {
    private String class_name;
    private String module_code;
    private String challenges;
    private String recommendations;
    private String dateSubmitted;

    // Constructor
    public Report(String class_name, String module_code, String challenges, String recommendations, String dateSubmitted) {
        this.class_name = class_name;
        this.module_code = module_code;
        this.challenges = challenges;
        this.recommendations= recommendations;
        this.dateSubmitted = dateSubmitted;
    }

    // Getters and Setters
    public String getClassName() {
        return class_name;
    }

    public void setClassName(String className) {
        this.class_name = className;
    }

    public String getModuleCode() {  // Changed getter to getModuleCode
        return module_code;
    }

    public void setModuleCode(String moduleCode) {  // Changed setter to setModuleCode
        this.module_code = moduleCode;
    }

    public String getChallenges() {
        return challenges;
    }

    public void setChallenges(String challenges) {
        this.challenges = challenges;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }
}
