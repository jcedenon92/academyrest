package com.jcedenon.service;

import com.jcedenon.model.Student;

import java.util.List;

public interface IStudentService extends ICRUD<Student, Integer>{

    List<Student> findByNames(String names);
    List<Student> findByNamesContains(String names);

    List<Student> findStudentsByOrderByAge(String order);

    List<Student> findAllOrder(String param);
}
