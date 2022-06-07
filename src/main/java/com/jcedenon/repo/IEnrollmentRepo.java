package com.jcedenon.repo;

import com.jcedenon.model.Enrollment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEnrollmentRepo extends IGenericRepo<Enrollment, Integer>{

    @Query(value = "select * from fn_enrollments()", nativeQuery = true)
    List<Object[]> callProcedure();

    /*
    ["Pedro Castillo"	"2022-06-05 17:29:50"]
    ["Alan Garcia"	"2022-06-05 17:29:50"]
    ["Keiko Fujimori"	"2022-05-29 18:31:50"]
    ["Pedro Castillo"	"2022-05-29 18:31:50"]
    ["Martin Vizcarra"	"2022-05-29 18:31:50"]
    ["Alejandro Toledo"	"2022-05-28 00:00:00"]
     */

    @Query(value = "select * from fn_enrollments_bystudent_andcourse()", nativeQuery = true)
    List<Object[]> callProcedure2();
}
