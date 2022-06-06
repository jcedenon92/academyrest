package com.jcedenon.controller;

import com.jcedenon.dto.StudentDTO;
import com.jcedenon.exceptions.ModelNotFoundException;
import com.jcedenon.model.Student;
import com.jcedenon.service.IStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private IStudentService service;

    @Autowired
//    @Qualifier("studentMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> readAll() throws Exception{
        List<StudentDTO> list = service.readAll().stream().map( c -> mapper.map(c, StudentDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Student stu = service.readById(id);
        if (stu == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        StudentDTO dto = mapper.map(stu, StudentDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO studentDTO) throws Exception{
        Student student = service.create(mapper.map(studentDTO, Student.class));
        StudentDTO dto = mapper.map(student, StudentDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentDTO> update(@Valid @RequestBody StudentDTO studentDTO) throws Exception{
        Student stu = service.readById(studentDTO.getId());
        if (stu == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + studentDTO.getId());
        }
        Student student = service.update(mapper.map(studentDTO, Student.class));
        StudentDTO dto = mapper.map(student, StudentDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Student stu = service.readById(id);
        if (stu == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //

    @GetMapping("/find/names/{param}")
    public ResponseEntity<List<StudentDTO>> findByNames(@PathVariable("param") String param) throws Exception{
        List<StudentDTO> list = service.findByNames(param).stream()
                .map( c -> mapper.map(c, StudentDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/find/names/like/{param}")
    public ResponseEntity<List<StudentDTO>> findByNamesLike(@PathVariable("param") String param) throws Exception{
        List<StudentDTO> list = service.findByNamesContains(param).stream()
                .map( c -> mapper.map(c, StudentDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/order/age/{asc}")
    public ResponseEntity<List<StudentDTO>> findByStudentsOrderByAge(@PathVariable("asc") String asc) throws Exception{
        List<StudentDTO> list = service.findStudentsByOrderByAge(asc).stream().map( c -> mapper.map(c, StudentDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<List<Student>> findAllOrder(@RequestParam(name = "param", defaultValue = "ASC") String param) throws Exception{
        List<Student> list = service.findAllOrder(param);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
