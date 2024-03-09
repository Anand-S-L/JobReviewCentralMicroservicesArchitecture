package com.anand.jobms;


import com.anand.jobms.dto.JobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;
    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> searcById(@PathVariable Long id) {
        JobDTO jobDTO = jobService.searcById(id);
        if (jobDTO != null) {
            return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if (!jobService.deleteById(id)) {
            return new ResponseEntity<>("does not exist", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("deleted Job with id " + id, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id,@RequestBody Job job) {
        if (jobService.updateById(id,job)) {
            return new ResponseEntity<>(String.format("updated job with id %d successfully",id), HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("Job with id %d not found",id), HttpStatus.NOT_FOUND);
    }
}
