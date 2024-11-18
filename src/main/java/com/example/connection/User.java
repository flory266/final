package com.example.connection;

public class User {


    private String class_name;
    private String module_code;
    private String challenges;
    private String recommendations;
    private int submissionDate;

    public User(String class_name,String module_code,String challenges,String recommendations, int submissionDate) {
        this.class_name = class_name;
        this.module_code = module_code;
        this.challenges = challenges;
        this.recommendations= recommendations;
        this.submissionDate = submissionDate;
    }
    public String getClass_name() {
        return class_name;
    }

    public String getModule_code() {
        return module_code;
    }

    public String getChallenges() {
        return challenges;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public int getSubmissionDate() {
        return submissionDate;
    }

}