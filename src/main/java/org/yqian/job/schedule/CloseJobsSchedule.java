package org.yqian.job.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yqian.job.service.JobService;

@Component
public class CloseJobsSchedule {

    private JobService jobService;

    public CloseJobsSchedule(JobService jobService) {
        this.jobService = jobService;
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void closeJobs() {
        jobService.findAllExpiredJobs().forEach(jobEntity -> jobService.closeJob(jobEntity));
    }
}
