package com.integradordh.trabajofinal.controllers;

import com.integradordh.trabajofinal.models.dto.AppointmentDTO;
import com.integradordh.trabajofinal.models.services.IAppointmentService;
import com.integradordh.trabajofinal.models.services.IDentistService;
import com.integradordh.trabajofinal.models.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalTime;
import java.util.Set;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    IAppointmentService appointmentService;

    @Autowired
    IPatientService patientService;

    @Autowired
    IDentistService dentistService;

    @PostMapping
    public ResponseEntity<?> saveAppointment(@RequestParam(name = "dentistLicense") String dentistLicense, @RequestParam(name = "patientNationalId") String patientNationalId, @RequestParam String date, @RequestParam LocalTime time)
    {
        AppointmentDTO appointmentDTO = new AppointmentDTO(dentistService.searchDentistByLicenseNumber(dentistLicense), patientService.searchPatientByNationalId(patientNationalId), date, time);
        appointmentService.saveAppointment(appointmentDTO);
        return ResponseEntity.ok().body("Appointment created succesfully " + appointmentDTO.toString());
    }

    @GetMapping("/id/{id}")
    public AppointmentDTO searchAppointmentById(@PathVariable Long id){
        return appointmentService.searchAppointmentById(id);
    }

    @GetMapping("/licenseNumber/{licenseNumber}")
    public Set<AppointmentDTO> searchAppointmentsByDentistLicense(@PathVariable String licenseNumber){
        return appointmentService.searchAppointmentsByDentistLicense(licenseNumber);
    }

    @GetMapping("/nationalId/{nationalId}")
    public Set<AppointmentDTO> searchAppointmentsByPatientNationalId(@PathVariable String nationalId){
        return appointmentService.searchAppointmentsByPatientNationalId(nationalId);
    }

    @GetMapping
    public Set<AppointmentDTO> searchAllAppointmentDTOS(){
        return appointmentService.searchAllAppointments();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateAppointment(@RequestBody AppointmentDTO appointmentDTO){
        appointmentService.updateAppointment(appointmentDTO);
        return ResponseEntity.ok().body("Appointment updated succesfully" + appointmentService.searchAppointmentById(appointmentDTO.getId()).toString());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") Long id){
        AppointmentDTO appointmentInfo = appointmentService.searchAppointmentById(id);
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().body("Appointment deleted succesfully " + appointmentInfo.toString());
    }
}
