package com.jcedenon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Integer id;

    @Size(min = 3, max = 100)
    private String nameCourse;

    @Size(min = 3, max = 20)
    private String acronymCourse;

    @Size(min = 3, max = 250)
    private boolean statusCourse;
}
