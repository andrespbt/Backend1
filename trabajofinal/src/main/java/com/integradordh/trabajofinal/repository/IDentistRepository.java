package com.integradordh.trabajofinal.repository;

import com.integradordh.trabajofinal.models.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDentistRepository extends JpaRepository<Dentist, Long> {

    @Query("select d from Dentist d where d.licenseNumber = ?1 and rownum <= 1")
    Dentist searchDentistByLicenseNumber(String licenseNumber);
}
