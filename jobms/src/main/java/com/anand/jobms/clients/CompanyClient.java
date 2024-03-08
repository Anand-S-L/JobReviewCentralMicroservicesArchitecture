package com.anand.jobms.clients;

import com.anand.jobms.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE")
public interface CompanyClient {
    @GetMapping("/companies/{companyId}")
    Company getCompany(@PathVariable("companyId") Long companyId);
}
