package com.jcedenon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDTO {

    private Integer id;
    private StudentDTO student;
    private LocalDateTime date;
    private boolean stateEnrollment;
    private List<EnrollmentDetailDTO> enrollmentDetails;
}
