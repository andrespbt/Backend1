package com.integradordh.trabajofinal.repository;

import com.integradordh.trabajofinal.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p where p.nationalId = ?1")
    Patient searchPatientByNationalId(String id);

}
