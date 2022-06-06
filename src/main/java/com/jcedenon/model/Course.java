package com.jcedenon.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCourse;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String acronym;

    @Column(nullable = false)
    private boolean status;
}
