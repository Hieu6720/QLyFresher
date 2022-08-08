package com.management.fresher.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "training_center")
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String address;
    private String description;
    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY)
    private Set<Fresher> freshers;

    public Center() {
    }

    public Center(Long id, String name, String address, String description, Set<Fresher> freshers) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.freshers = freshers;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Fresher> getFreshers() {
        return freshers;
    }

    public void setFreshers(Set<Fresher> fresherSet) {
        this.freshers = fresherSet;
    }
}
