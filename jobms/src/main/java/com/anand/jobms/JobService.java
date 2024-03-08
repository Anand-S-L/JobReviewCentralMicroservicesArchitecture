package com.anand.jobms;


import com.anand.jobms.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);

    JobDTO searcById(Long id);

    Boolean deleteById(Long id);

    boolean updateById(Long id, Job job);
}
