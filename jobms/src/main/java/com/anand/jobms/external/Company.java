package com.anand.jobms.external;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Company {
    private Long id;
    private String name;
    private String description;

}
