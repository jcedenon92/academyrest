package com.jcedenon;

import com.jcedenon.dto.CourseDTO;
import com.jcedenon.dto.EnrollmentDTO;
import com.jcedenon.model.Course;
import com.jcedenon.model.Enrollment;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {

    @Primary
    @Bean("courseMapper")
    public ModelMapper courseMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<CourseDTO, Course> typeMap = mapper.createTypeMap(CourseDTO.class, Course.class);
        typeMap.addMapping(CourseDTO::getId, Course::setIdCourse);
        typeMap.addMapping(CourseDTO::getNameCourse, Course::setName);
        typeMap.addMapping(CourseDTO::getAcronymCourse, Course::setAcronym);
        typeMap.addMapping(CourseDTO::isStatusCourse, Course::setStatus);
        return new ModelMapper();
    }

    @Bean("studentMapper")
    public ModelMapper studentMapper(){
        return new ModelMapper();
    }

    @Bean("enrollmentMapper")
    public ModelMapper enrollmentMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<EnrollmentDTO, Enrollment> typeMap = mapper.createTypeMap(EnrollmentDTO.class, Enrollment.class);
        typeMap.addMapping(EnrollmentDTO::getId, Enrollment::setIdEnrollment);
        typeMap.addMapping(EnrollmentDTO::getStudent, (dest, v) -> dest.getStudent().setIdStudent((Integer) v));
        typeMap.addMapping(EnrollmentDTO::getEnrollmentDetails, Enrollment::setDetails);
        typeMap.addMapping(EnrollmentDTO::isStateEnrollment, Enrollment::setState);
        return new ModelMapper();
    }

//    @Bean("detailMapper")
//    public ModelMapper detailMapper(){
//        ModelMapper mapper = new ModelMapper();
//        TypeMap<EnrollmentDetailDTO, EnrollmentDetail> typeMap = mapper.createTypeMap(EnrollmentDetailDTO.class, EnrollmentDetail.class);
//        typeMap.addMapping(EnrollmentDetailDTO::getIdEnrollment, (dest, v) -> dest.getEnrollment().setIdEnrollment((Integer) v));
//        typeMap.addMapping(EnrollmentDetailDTO::getCourse, (dest, v) -> dest.getCourse().setIdCourse((Integer) v));
//        typeMap.addMapping(EnrollmentDetailDTO::getClassroomName, EnrollmentDetail::setClassroom);
//        return new ModelMapper();
//    }
}
