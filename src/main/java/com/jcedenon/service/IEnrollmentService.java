package com.jcedenon.service;

import com.jcedenon.model.Course;
import com.jcedenon.model.Enrollment;
import com.jcedenon.model.EnrollmentDetail;
import com.jcedenon.model.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IEnrollmentService extends ICRUD<Enrollment,Integer>{

    Enrollment saveTransactional(Enrollment enrollment, List<EnrollmentDetail> details);

    Map<String, String> getStudentsByCourse();

    Map<String, Integer> getQuantityStudentsByCourse();
}
