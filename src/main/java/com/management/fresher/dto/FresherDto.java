package com.management.fresher.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class FresherDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String programLanguage;
    @Email
    private String email;
    private Float testScore1;
    private Float testScore2;
    private Float testScore3;
    private Float finalScore;

    public FresherDto(){

    }

    public FresherDto(Long id, String name, String programLanguage, String email, Float testScore1, Float testScore2, Float testScore3, Float finalScore) {
        this.id = id;
        this.name = name;
        this.programLanguage = programLanguage;
        this.email = email;
        this.testScore1 = testScore1;
        this.testScore2 = testScore2;
        this.testScore3 = testScore3;
        this.finalScore = finalScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramLanguage() {
        return programLanguage;
    }

    public void setProgramLanguage(String programLanguage) {
        this.programLanguage = programLanguage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getTestScore1() {
        return testScore1;
    }

    public void setTestScore1(Float testScore1) {
        this.testScore1 = testScore1;
    }

    public Float getTestScore2() {
        return testScore2;
    }

    public void setTestScore2(Float testScore2) {
        this.testScore2 = testScore2;
    }

    public Float getTestScore3() {
        return testScore3;
    }

    public void setTestScore3(Float testScore3) {
        this.testScore3 = testScore3;
    }

    public Float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Float finalScore) {
        this.finalScore = finalScore;
    }
}
