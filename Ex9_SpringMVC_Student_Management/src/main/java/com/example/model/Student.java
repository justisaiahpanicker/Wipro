package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Step 5: entity class mapped to the "student" table using JPA annotations.
 */
@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "regno")
    private int regno;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "cgpa", nullable = false)
    private double cgpa;

    public Student() {
    }

    public Student(int regno, String name, double cgpa) {
        this.regno = regno;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getRegno() {
        return regno;
    }

    public void setRegno(int regno) {
        this.regno = regno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}
