package com.integradordh.trabajofinal.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integradordh.trabajofinal.exceptions.BadRequestException;
import com.integradordh.trabajofinal.exceptions.ResourceNotFoundException;
import com.integradordh.trabajofinal.models.Appointment;
import com.integradordh.trabajofinal.models.Dentist;
import com.integradordh.trabajofinal.models.Patient;
import com.integradordh.trabajofinal.models.dto.AppointmentDTO;
import com.integradordh.trabajofinal.services.IAppointmentService;
import com.integradordh.trabajofinal.services.IDentistService;
import com.integradordh.trabajofinal.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<?> saveAppointment(@RequestParam(name = "dentistLicense") String dentistLicense, @RequestParam(name = "patientNationalId") String patientNationalId, @RequestBody AppointmentDTO appointmentDTO) throws ResourceNotFoundException, BadRequestException {
        ResponseEntity<?> response = null;

        try {
            Appointment appointment = new Appointment(objectMapper.convertValue(dentistService.searchDentistDTOCompleteByLicenseNumber(dentistLicense), Dentist.class), objectMapper.convertValue(patientService.searchPatientCompleteByNationalId(patientNationalId), Patient.class), appointmentDTO.getDate(), appointmentDTO.getTime());
            appointmentService.saveAppointment(appointment);
            response = ResponseEntity.status(HttpStatus.CREATED).body("Appointment created successfully " + appointment.toString());

        }catch (BadRequestException e){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment couldn't be created.");
            e.printStackTrace();

        }
        return response;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> searchAppointmentById(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;

        try {

            AppointmentDTO appointmentDTO = appointmentService.searchAppointmentById(id);
            response = ResponseEntity.status(HttpStatus.FOUND).body(appointmentDTO);

        }catch (ResourceNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found.");
            e.printStackTrace();

        }
        return response;
    }

    @GetMapping("/licenseNumber/{licenseNumber}")
    public ResponseEntity<?> searchAppointmentsByDentistLicense(@PathVariable String licenseNumber) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;

        try {

            Set<AppointmentDTO> appointmentDTOSet = appointmentService.searchAppointmentsByDentistLicense(licenseNumber);
            response = ResponseEntity.status(HttpStatus.FOUND).body(appointmentDTOSet);

        }catch (ResourceNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointments not found.");
            e.printStackTrace();

        }
        return response;
    }

    @GetMapping("/nationalId/{nationalId}")
    public ResponseEntity<?> searchAppointmentsByPatientNationalId(@PathVariable String nationalId) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;

        try {

            Set<AppointmentDTO> appointmentDTOSet = appointmentService.searchAppointmentsByPatientNationalId(nationalId);
            response = ResponseEntity.status(HttpStatus.FOUND).body(appointmentDTOSet);

        }catch (ResourceNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointments not found.");
            e.printStackTrace();

        }
        return response;
    }

    @GetMapping
    public Set<AppointmentDTO> searchAllAppointmentDTOS(){
        return appointmentService.searchAllAppointments();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment) throws ResourceNotFoundException, BadRequestException {
        ResponseEntity<?> response = null;
        try {
            appointmentService.updateAppointment(appointment);
            response = ResponseEntity.ok().body("Appointment updated successfully" + appointmentService.searchAppointmentById(appointment.getId()).toString());


        }catch (BadRequestException e){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment couldn't be updated.");
            e.printStackTrace();
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") Long id) throws ResourceNotFoundException {
        ResponseEntity<?> response = null;

        try {
            AppointmentDTO appointmentInfo = appointmentService.searchAppointmentById(id);
            appointmentService.deleteAppointment(id);
            response = ResponseEntity.ok().body("Appointment deleted successfully " + appointmentInfo.toString());

        }catch (ResourceNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment couldn't be deleted.");
            e.printStackTrace();

        }
        return response;
    }
}
