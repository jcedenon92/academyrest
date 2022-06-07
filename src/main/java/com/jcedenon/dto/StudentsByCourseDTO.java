package com.jcedenon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentsByCourseDTO {

    private String studentfn;
    private String coursefn;
    private String dateenrollfn;
}
