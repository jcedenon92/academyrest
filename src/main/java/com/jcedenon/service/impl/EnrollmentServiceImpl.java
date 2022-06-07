package com.jcedenon.service.impl;

import com.jcedenon.dto.ProcedureDTO;
import com.jcedenon.dto.StudentsByCourseDTO;
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

    @Override
    public List<ProcedureDTO> callProcedure() {
        /*List<Object[]>
        ["Pedro Castillo"	"2022-06-05 17:29:50"]
        ["Alan Garcia"	"2022-06-05 17:29:50"]
        ["Keiko Fujimori"	"2022-05-29 18:31:50"]
        ["Pedro Castillo"	"2022-05-29 18:31:50"]
        ["Martin Vizcarra"	"2022-05-29 18:31:50"]
        ["Alejandro Toledo"	"2022-05-28 00:00:00"]
         */
        List<ProcedureDTO> list = new ArrayList<>();
        repo.callProcedure().forEach(el -> {
            ProcedureDTO dto = new ProcedureDTO();
            dto.setStudentfn(String.valueOf(el[0]));
            dto.setDateenrollfn(String.valueOf(el[1]));
            list.add(dto);
        });
        return list;
    }

    @Override
    public List<StudentsByCourseDTO> callProcedure2() {
        /*
        ["Alejandro Toledo"	"Programacion"	                        "2022-05-28 00:00:00"]
        ["Alejandro Toledo"	"Base de Datos"	                        "2022-05-28 00:00:00"]
        ["Alejandro Toledo"	"Arquitectura de Redes"	                "2022-05-28 00:00:00"]
        ["Pedro Castillo"	"Arquitectura de Redes"	                "2022-05-29 18:31:50"]
        ["Martin Vizcarra"	"Arquitectura de Redes"	                "2022-05-29 18:31:50"]
        ["Keiko Fujimori"	"Base de Datos"	                        "2022-05-29 18:31:50"]
        ["Pedro Castillo"	"Cloud Computing"	                    "2022-06-05 17:29:50"]
        ["Pedro Castillo"	"Inteligencia Artificial con Python"	"2022-06-05 17:29:50"]
        ["Alan Garcia"	    "Programacion"	                        "2022-06-05 17:29:50"]
        ["Alan Garcia"	    "Cloud Computing"	                    "2022-06-05 17:29:50"]
        ["Alan Garcia"	    "Inteligencia Artificial con Python"	"2022-06-05 17:29:50"]
         */

        List<StudentsByCourseDTO> list = new ArrayList<>();
        repo.callProcedure2().forEach(el -> {
            StudentsByCourseDTO dto = new StudentsByCourseDTO();
            dto.setStudentfn((String) el[0]);
            dto.setCoursefn((String) el[1]);
            dto.setDateenrollfn((String) el[2]);
            list.add(dto);
        });
        return list;
    }
}










