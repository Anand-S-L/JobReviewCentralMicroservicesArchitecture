package com.anand.jobms.dto;

import com.anand.jobms.Job;
import com.anand.jobms.external.Company;
import lombok.Data;

@Data
public class JobWithCompanyDTO {
    private Job job;
    private Company company;
}
