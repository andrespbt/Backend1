package com.integradordh.trabajofinal.repository;

import com.integradordh.trabajofinal.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select a from Appointment a where a.dentist.licenseNumber = ?1")
    Set<Appointment> searchAppointmentsByDentistLicense(String dentistLicense);

    @Query("select a from Appointment a where a.patient.nationalId = ?1")
    Set<Appointment> searchAppointmentsByPatientNationalId(String dentistLicense);


}
