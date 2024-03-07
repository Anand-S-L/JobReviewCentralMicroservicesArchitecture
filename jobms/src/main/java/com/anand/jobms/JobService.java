package com.anand.jobms;


import com.anand.jobms.Job;
import com.anand.jobms.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);

    Job searcById(Long id);

    Boolean deleteById(Long id);

    boolean updateById(Long id, Job job);
}
