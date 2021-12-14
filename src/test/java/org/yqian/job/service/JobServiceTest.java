package org.yqian.job.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

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

        JobEntity result = jobService.postJob(job);
        assertThat(result.getName()).isEqualTo("New Job");
    }
}
