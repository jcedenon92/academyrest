package com.jcedenon.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jcedenon.model.Course;
import com.jcedenon.model.Enrollment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDetailDTO {

    @JsonIgnore
    private EnrollmentDTO enrollment;
    private CourseDTO course;
    private String classroom;
}
