package org.example;

import java.util.Date;

public class Employee {

    private int id;
    private String name;
    private int age;
    private String company;
    private Date workingSince;

    public Employee(int id, String name, int age, String company, Date workingSince) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.company = company;
        this.workingSince = workingSince;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getWorkingSince() {
        return workingSince;
    }

    public void setWorkingSince(Date workingSince) {
        this.workingSince = workingSince;
    }
}
