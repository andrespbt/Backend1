package com.trabajointegrador.trabajointegradordh.model;

public class User {

    private Long id;

    private String name;
    private String lastName;
    private String role;

    public User() {
    }

    public User(String name, String lastName, String role) {
        this.name = name;
        this.lastName = lastName;
        this.role = role;
    }

    public User(Long id, String name, String lastName, String role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "\nUser: \n"+ "Name: " + name + "\nLastname: " + lastName + "\nRole: " + role;
    }
}
