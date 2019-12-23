package com.example.api.model;

public class EmployeeCUD {
    private int id;
    private String name;
    private float salary;
    private int age;
    private String profile_image;

    public EmployeeCUD(String employee_name, float employee_salary, int employee_age    ) {

        this.name = employee_name;
        this.salary = employee_salary;
        this.age = employee_age;

    }
}
