package com.jcedenon.service.impl;

import com.jcedenon.model.Enrollment;
import com.jcedenon.model.EnrollmentDetail;
import com.jcedenon.repo.IEnrollmentRepo;
import com.jcedenon.repo.IGenericRepo;
import com.jcedenon.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
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

    @Override
    public Map<String, Integer> getQuantityStudentsByCourse() {
        Stream<List<EnrollmentDetail>> stream = repo.findAll().stream().map(Enrollment::getDetails); // e->e.getDetails()
        Stream<EnrollmentDetail> streamDetail = stream.flatMap(Collection::stream);

        Map<String, Integer> map = streamDetail.collect(Collectors.groupingBy(d -> d.getCourse().getName(),
                Collectors.summingInt(d -> 1)));

        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // e->e.getKey(),
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)
                );
    }
}










