package com.jcedenon.service.impl;

import com.jcedenon.model.Course;
import com.jcedenon.repo.ICourseRepo;
import com.jcedenon.repo.IGenericRepo;
import com.jcedenon.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends CRUDImpl<Course, Integer> implements ICourseService {

    @Autowired
    private ICourseRepo repo;

    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return repo;
    }
}
