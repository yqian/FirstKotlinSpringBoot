package org.yqian.job.service;

import org.springframework.stereotype.Service;
import org.yqian.job.entity.JobEntity;
import org.yqian.job.repository.JobRepository;

import java.util.List;

@Service
public class JobService {

    private JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobEntity postJob(JobEntity job) {
        return jobRepository.save(job);
    }

    public List<JobEntity> findMostActiveJobs() {
        return jobRepository.findMostActiveJobs();
    }

    public List<JobEntity> findMostRecentJobs() {
        return jobRepository.findMostRecentJobs();
    }
}
