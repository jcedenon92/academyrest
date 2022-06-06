package com.jcedenon.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStudent;

    @Column(length = 150, nullable = false)
    private String names;

    @Column(length = 200, nullable = false)
    private String surname;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column(length = 50, nullable = false)
    private Integer age;
}
