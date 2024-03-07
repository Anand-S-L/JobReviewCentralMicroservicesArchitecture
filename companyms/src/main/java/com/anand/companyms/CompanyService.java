package com.anand.companyms;


import com.anand.companyms.Company;
import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    Company addCompany(Company company);

    Boolean deleteById(Long id);

    Company getById(Long id);

    Boolean updateById(Long id, Company company);
}
