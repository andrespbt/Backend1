package com.integradordh.trabajofinal.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    Long id;
    private String name;
    private String lastName;

    public UserDTO(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "\n" +
                "Name='" + name + '\'' +
                "\nLastName='" + lastName + '\'';
    }
}
