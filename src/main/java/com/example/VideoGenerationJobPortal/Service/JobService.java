package com.example.VideoGenerationJobPortal.Service;

import com.example.VideoGenerationJobPortal.Model.Job;
import com.example.VideoGenerationJobPortal.Model.JobStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class JobService {

    private final Map<String, Job> db=new HashMap<>();

    private static final Map<JobStatus, JobStatus> transitions = Map.of(
            JobStatus.PENDING, JobStatus.PROCESSING,
            JobStatus.PROCESSING, JobStatus.COMPLETED,
            JobStatus.COMPLETED, JobStatus.FAILED,
            JobStatus.FAILED, JobStatus.PENDING
    );

    public Job createJob(String productImageUrl, String template) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        Job job = new Job(id, productImageUrl, template, JobStatus.PENDING, now);
        db.put(id, job);
        return job;
    }

    public Optional<Job> getJobById(String id) {
        return Optional.ofNullable(db.get(id));
    }


    public List<Job> getAllJobsLatestFirst() {
        List<Job> list = new ArrayList<>(db.values());
        list.sort(Comparator.comparing(Job::getCreatedAt).reversed());
        return list;
    }


    public Optional<Job> updateJobStatus(String id, JobStatus newStatus) {
        Job job = db.get(id);
        if (job == null) {
            return Optional.empty();
        }

        JobStatus currentStatus = job.getStatus();
        JobStatus allowedNextStatus = transitions.getOrDefault(currentStatus, null);

        if (allowedNextStatus!=newStatus) {
            throw new IllegalArgumentException(
                    String.format("Invalid status transition from '%s' to '%s'. Allowed next: %s",
                            currentStatus,
                            newStatus,
                            allowedNextStatus==null ? "none" : allowedNextStatus)
            );
        }

        job.setStatus(newStatus);
        db.put(id, job);

        return Optional.of(job);
    }
}
