package com.anand.jobms.external;


import jakarta.persistence.*;
import lombok.Data;

@Data
public class Review {
    private Long id;
    private String title;
    private String description;
    private double rating;
}
