package com.anand.companyms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        return new ResponseEntity<>(companyService.addCompany(company), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        if (companyService.deleteById(id)) {
            return new ResponseEntity<>(String.format("Deleted Company with id %d", id), HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("Company with id %d not found", id), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Long id) {
        Company company = companyService.getById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id, @RequestBody Company company) {
        if (companyService.updateById(id, company)) {
            return new ResponseEntity<>(String.format("Updated company with id %d", id), HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("company with id %d not found", id), HttpStatus.NOT_FOUND);
    }
}
