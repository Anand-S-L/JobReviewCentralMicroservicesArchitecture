package com.anand.jobms.impl;


import com.anand.jobms.Job;
import com.anand.jobms.JobRepository;
import com.anand.jobms.JobService;
import com.anand.jobms.dto.JobWithCompanyDTO;
import com.anand.jobms.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobRepository jobRepository;

    private JobWithCompanyDTO convertToDto(Job job) {
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8083/companies/" + job.getCompanyId(), Company.class);
        jobWithCompanyDTO.setCompany(company);
        return jobWithCompanyDTO;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        return jobRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job searcById(Long id) {
        return jobRepository.findById(id).orElse(null);
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
