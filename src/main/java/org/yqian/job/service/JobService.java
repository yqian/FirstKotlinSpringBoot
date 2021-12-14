package org.yqian.job.service;

import org.springframework.stereotype.Service;
import org.yqian.job.entity.JobEntity;
import org.yqian.job.repository.JobRepository;

import java.util.Optional;

@Service
public class JobService {

    private JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    public JobEntity postJob(JobEntity job) {
        return jobRepository.save(job);
    }
}
