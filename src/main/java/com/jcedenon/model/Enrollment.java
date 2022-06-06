package com.jcedenon.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnrollment;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false, foreignKey = @ForeignKey(name = "fk_enrollment_student"))
    private Student student;

    @Column(nullable = false)
    private LocalDateTime dateEnrollment;

    @Column(nullable = false)
    private boolean state;

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<EnrollmentDetail> details;
}
