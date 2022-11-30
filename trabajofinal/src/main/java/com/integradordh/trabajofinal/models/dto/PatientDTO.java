package com.integradordh.trabajofinal.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO extends UserDTO {

    private String nationalId;
    private String registrationDate;

    private String address;

    public PatientDTO(Long id, String name, String lastName, String nationalId, String registrationDate, String address) {
        super(id, name, lastName);
        this.nationalId = nationalId;
        this.registrationDate = registrationDate;
        this.address = address;
    }

    public PatientDTO(String name, String lastName, String nationalId, String registrationDate, String address) {
        super(name, lastName);
        this.nationalId = nationalId;
        this.registrationDate = registrationDate;
        this.address = address;
    }

    public PatientDTO(String name, String lastName, String nationalId, Date registrationDate) {
        super(name, lastName);
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(registrationDate);
        this.nationalId = nationalId;
        this.registrationDate = stringDate;
    }

    public PatientDTO(String name, String lastName, String nationalId, String registrationDate) {
        super(name, lastName);
        this.nationalId = nationalId;
        this.registrationDate = registrationDate;
    }



    @Override
    public String toString() {

        return "\nName = " + this.getName() +
                "\nLastname = " + this.getLastName() +
                "\nNational id = " + this.nationalId +
                "\nAddress = " + this.address +
                "\nRegistration date = " + this.getRegistrationDate();
    }
}
