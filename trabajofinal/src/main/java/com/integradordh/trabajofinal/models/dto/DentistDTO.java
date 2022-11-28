package com.integradordh.trabajofinal.models.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DentistDTO extends UserDTO {


    private String licenseNumber;

    public DentistDTO(String name, String lastName, String licenseNumber) {
        super(name, lastName);
        this.licenseNumber = licenseNumber;
    }

    public DentistDTO(Long id, String name, String lastName, String licenseNumber) {
        super(id, name, lastName);
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String toString() {
        return "\nName = " + this.getName() +
                "\nLastname = " + this.getLastName() +
                "\nLicense number = " + this.licenseNumber;
    }
}
