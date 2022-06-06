package com.jcedenon.service.impl;

import com.jcedenon.model.Student;
import com.jcedenon.repo.IGenericRepo;
import com.jcedenon.repo.IStudentRepo;
import com.jcedenon.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends CRUDImpl<Student, Integer> implements IStudentService {

    @Autowired
    private IStudentRepo repo;

    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Student> findByNames(String names) {
        return repo.findByNames(names);
    }

    @Override
    public List<Student> findByNamesContains(String names) {
        return repo.findByNamesContains(names);
    }

    @Override
    public List<Student> findStudentsByOrderByAge(String order) {
        if (order.equals("desc")) {
            return repo.findStudentsByOrderByAgeDesc();
        } else if (order.equals("asc")) {
            return repo.findStudentsByOrderByAgeAsc();
        }
        return null;
    }

    @Override
    public List<Student> findAllOrder(String param) {
        Sort.Direction direction = param.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return repo.findAll(Sort.by(direction, "age"));
    }
}
