package com.anand.jobms.dto;

import com.anand.jobms.external.Company;
import com.anand.jobms.external.Review;
import lombok.Data;

import java.util.List;

@Data
public class JobDTO {
    private long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> review;
}
