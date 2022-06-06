package com.jcedenon.service.impl;

import com.jcedenon.model.Course;
import com.jcedenon.model.Enrollment;
import com.jcedenon.model.EnrollmentDetail;
import com.jcedenon.model.Student;
import com.jcedenon.repo.IEnrollmentRepo;
import com.jcedenon.repo.IGenericRepo;
import com.jcedenon.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EnrollmentServiceImpl extends CRUDImpl<Enrollment, Integer> implements IEnrollmentService {

    @Autowired
    private IEnrollmentRepo repo;

    @Override
    protected IGenericRepo<Enrollment, Integer> getRepo() {
        return repo;
    }

    @Override
    public Enrollment saveTransactional(Enrollment enrollment, List<EnrollmentDetail> details) {
        details.forEach(d -> d.setEnrollment(enrollment));
        enrollment.setDetails(details);

        return repo.save(enrollment);
    }

    @Override
    public Map<String, String> getStudentsByCourse() {
        Stream<List<EnrollmentDetail>> stream = repo.findAll().stream().map(Enrollment::getDetails); // e->e.getDetails()
        Stream<EnrollmentDetail> streamDetail = stream.flatMap(Collection::stream);

//        Map<Course, List<Student>> map = streamDetail.collect(Collectors.groupingBy(EnrollmentDetail::getCourse,
//                Collectors.mapping(d -> d.getEnrollment().getStudent(), Collectors.toList())));

//        Map<String, List<Student>> map = streamDetail.collect(Collectors.groupingBy(d -> d.getCourse().getName(),
//                Collectors.mapping(d -> d.getEnrollment().getStudent(), Collectors.toList())));

        Map<String, String> map = streamDetail.collect(Collectors.groupingBy(d -> d.getCourse().getName(),
                Collectors.mapping(d -> d.getEnrollment().getStudent().getNames() + " " + d.getEnrollment().getStudent().getSurname(), Collectors.joining(", "))));

        return map;
    }
}










