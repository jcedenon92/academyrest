package com.jcedenon.controller;

import com.jcedenon.dto.CourseDTO;
import com.jcedenon.exceptions.ModelNotFoundException;
import com.jcedenon.model.Course;
import com.jcedenon.service.ICourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private ICourseService service;

    @Autowired
    @Qualifier("courseMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> readAll() throws Exception{
        List<CourseDTO> list = service.readAll().stream()
                .map( c -> mapper.map(c, CourseDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Course cou = service.readById(id);
        if (cou == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        CourseDTO dto = mapper.map(cou, CourseDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO courseDTO) throws Exception{
        Course course = service.create(mapper.map(courseDTO, Course.class));
        CourseDTO dto = mapper.map(course, CourseDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CourseDTO> update(@Valid @RequestBody CourseDTO courseDTO) throws Exception{
        Course cou = service.readById(courseDTO.getId());
        if (cou == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + courseDTO.getId());
        }
        Course course = service.update(mapper.map(courseDTO, Course.class));
        CourseDTO dto = mapper.map(course, CourseDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Course cou = service.readById(id);
        if (cou == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
