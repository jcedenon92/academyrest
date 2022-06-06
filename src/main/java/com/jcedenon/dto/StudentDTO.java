package com.jcedenon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Integer id;

    @Size(min = 3, max = 150)
    private String namesStudent;

    @Size(min = 3, max = 200)
    private String surnameStudent;

    @Size(min = 8, max = 8)
    private String dniStudent;

    private Integer ageStudent;
}
