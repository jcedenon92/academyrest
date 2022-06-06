package com.jcedenon.repo;

import com.jcedenon.model.Student;

import java.util.List;

public interface IStudentRepo extends IGenericRepo<Student,Integer> {

    //derivedQueries
    //SELECT * FROM Student WHERE names = ?
    List<Student> findByNames(String names);

    //SELECT * FROM Student WHERE names LIKE '%org%'
    List<Student> findByNamesContains(String names);

    /*
    SELECT * FROM Student ORDER BY age DESC
     */
    List<Student> findStudentsByOrderByAgeDesc();
    List<Student> findStudentsByOrderByAgeAsc();

}
