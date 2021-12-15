package org.yqian.job.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yqian.job.entity.JobEntity;
import org.yqian.job.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JobServiceTest {

    @Mock
    private JobRepository mockRepository;

    @InjectMocks
    private JobService jobService;

    @Test
    public void testPostJob() {
        JobEntity job = new JobEntity();
        job.setName("New Job");

        given(mockRepository.save(job)).willReturn(job);
        JobEntity result = jobService.postJob(job);
        assertThat(result.getName()).isEqualTo("New Job");
    }

    @Test
    public void testFindMostActiveJobs() {
        List<JobEntity> jobEntityList = new ArrayList<>();
        given(mockRepository.findMostActiveJobs()).willReturn(jobEntityList);
        List<JobEntity> result = jobService.findMostActiveJobs();
        assertThat(result).isNotEmpty();
    }
}
