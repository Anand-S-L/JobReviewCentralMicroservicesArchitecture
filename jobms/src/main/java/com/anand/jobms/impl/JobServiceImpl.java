package com.anand.jobms.impl;


import com.anand.jobms.Job;
import com.anand.jobms.JobRepository;
import com.anand.jobms.JobService;
import com.anand.jobms.clients.ReviewClient;
import com.anand.jobms.clients.CompanyClient;
import com.anand.jobms.dto.JobDTO;
import com.anand.jobms.external.Company;
import com.anand.jobms.external.Review;
import com.anand.jobms.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobRepository jobRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private CompanyClient CompanyClient;
    @Autowired
    private ReviewClient reviewClient;

    private JobDTO convertToDto(Job job) {
        Company company = CompanyClient.getCompany(job.getCompanyId());
        List<Review> reviewResponse=reviewClient.getReviews(job.getCompanyId());
        return JobMapper.mapToJobDto(job,company,reviewResponse);
    }

    @Override
    public List<JobDTO> findAll() {
        return jobRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO searcById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            if (updatedJob.getDescription() != null) {
                job.setDescription(updatedJob.getDescription());
            }
            if (updatedJob.getLocation() != null) {
                job.setLocation(updatedJob.getLocation());
            }
            if (updatedJob.getTitle() != null) {
                job.setTitle(updatedJob.getTitle());
            }
            if (updatedJob.getMinSalary() != null) {
                job.setMinSalary(updatedJob.getMinSalary());
            }
            if (updatedJob.getMaxSalary() != null) {
                job.setMaxSalary(updatedJob.getMaxSalary());
            }
            jobRepository.save(job);
            return true;
        }

        return false;
    }
}
