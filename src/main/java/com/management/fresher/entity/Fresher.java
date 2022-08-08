package com.management.fresher.entity;

import javax.persistence.*;

@Entity
@Table(name = "freshers")
public class Fresher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String programLanguage;
    @Column(nullable = false)
    private String email;
    private Float testScore1;
    private Float testScore2;
    private Float testScore3;
    private Float finalScore;

    @ManyToOne
    @JoinColumn(name="center_id")
    private Center center;

    public Fresher() {
    }

    public Fresher(Long id, String name, String programLanguage, String email, float testScore1, float testScore2, float testScore3, float finalScore, Center center) {
        this.id = id;
        this.name = name;
        this.programLanguage = programLanguage;
        this.email = email;
        this.testScore1 = testScore1;
        this.testScore2 = testScore2;
        this.testScore3 = testScore3;
        this.finalScore = finalScore;
        this.center = center;
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

    public Center getTrainingCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }
}
