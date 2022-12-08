package com.integradordh.trabajofinal.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDTO {

    private String name;
    private String lastName;
    private String nationalId;





    @Override
    public String toString() {

        return "\nName = " + this.getName() +
                "\nLastname = " + this.getLastName() +
                "\nNational id = " + this.nationalId;
    }
}
