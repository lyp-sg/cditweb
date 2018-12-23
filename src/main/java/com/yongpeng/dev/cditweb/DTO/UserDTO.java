package com.yongpeng.dev.cditweb.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserDTO {

    private final String name;
    private final double salary;

    public UserDTO(String name,double salary){
        this.name = name;
        this.salary = salary;
    }

    public String getName(){
        return this.name;
    }

    public double getSalary(){
        return this.salary;
    }


}
