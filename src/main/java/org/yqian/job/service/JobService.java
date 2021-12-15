package org.yqian.job.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yqian.job.entity.JobEntity;
import org.yqian.job.repository.JobRepository;

import java.util.List;

@Slf4j
@Service
public class JobService {

    private JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobEntity postJob(JobEntity job) {
        job.setRequirement(job.getRequirement().substring(0, Math.min(job.getRequirement().length(), 16384)));
        job.setDescription(job.getDescription().substring(0, Math.min(job.getDescription().length(), 16384)));
        log.info("Job description length is " + job.getRequirement().length());
        return jobRepository.save(job);
    }

    public List<JobEntity> findMostActiveJobs() {
        return jobRepository.findMostActiveJobs();
    }

    public List<JobEntity> findMostRecentJobs() {
        return jobRepository.findMostRecentJobs();
    }
}
