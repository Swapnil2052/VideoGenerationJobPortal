package com.example.VideoGenerationJobPortal.Controller;

import com.example.VideoGenerationJobPortal.DTO.JobRequestDTO;
import com.example.VideoGenerationJobPortal.DTO.UpdateJobStatusDTO;
import com.example.VideoGenerationJobPortal.Model.Job;
import com.example.VideoGenerationJobPortal.Model.JobStatus;
import com.example.VideoGenerationJobPortal.Service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@Valid @RequestBody JobRequestDTO request) {
        Job job = jobService.createJob(
                request.getProductImageUrl(),
                request.getTemplate()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(job);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable String id) {
        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobsLatestFirst();
        return ResponseEntity.ok(jobs);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Job> updateJobStatus(@PathVariable String id,
                                               @Valid @RequestBody UpdateJobStatusDTO request) {
        JobStatus newStatus = request.getStatus();

        try {
              return jobService.updateJobStatus(id, newStatus)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
