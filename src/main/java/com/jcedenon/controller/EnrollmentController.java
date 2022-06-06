package com.jcedenon.controller;

import com.jcedenon.dto.EnrollmentDTO;
import com.jcedenon.exceptions.ModelNotFoundException;
import com.jcedenon.model.Course;
import com.jcedenon.model.Enrollment;
import com.jcedenon.model.Student;
import com.jcedenon.service.IEnrollmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private IEnrollmentService service;

    @Autowired
    @Qualifier("enrollmentMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> readAll() throws Exception{
        List<EnrollmentDTO> list = service.readAll().stream()
                .map(c -> mapper.map(c, EnrollmentDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Enrollment enr = service.readById(id);
        if (enr == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        EnrollmentDTO dto = mapper.map(enr, EnrollmentDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> create(@Valid @RequestBody EnrollmentDTO enrollmentDTO) throws Exception{
        Enrollment e = mapper.map(enrollmentDTO, Enrollment.class);
        Enrollment enr = service.saveTransactional(e, e.getDetails());
        EnrollmentDTO dto = mapper.map(enr, EnrollmentDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EnrollmentDTO> update(@Valid @RequestBody EnrollmentDTO enrollmentDTO) throws Exception{
        Enrollment enr = service.readById(enrollmentDTO.getId());
        if (enr == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + enrollmentDTO.getId());
        }
        Enrollment enrollment = service.update(mapper.map(enrollmentDTO, Enrollment.class));
        EnrollmentDTO dto = mapper.map(enrollment, EnrollmentDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> delete(@PathVariable("id") Integer id) throws Exception{
        Enrollment enr = service.readById(id);
        if (enr == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /////////////////////////////////////////////////////////////

    @GetMapping("/students/by/course")
    public ResponseEntity<Map<String, String>> getStudentsByCourse() throws Exception{
        Map<String, String> map = service.getStudentsByCourse();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
