package com.anand.jobms.mapper;

import com.anand.jobms.Job;
import com.anand.jobms.dto.JobDTO;
import com.anand.jobms.external.Company;
import com.anand.jobms.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobDto(Job job, Company company, List<Review> review) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReview(review);
        return jobDTO;
    }
}
