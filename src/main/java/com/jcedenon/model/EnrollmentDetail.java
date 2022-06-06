package com.jcedenon.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EnrollmentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnrollmentDetail;

    @ManyToOne
    @JoinColumn(name = "id_enrollment", nullable = false, foreignKey = @ForeignKey(name = "fk_enrollmentdetail_enrollment"))
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false, foreignKey = @ForeignKey(name = "fk_enrollmentdetail_course"))
    private Course course;

    @Column(length = 150, nullable = false)
    private String classroom;
}
