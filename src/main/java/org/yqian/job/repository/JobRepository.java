package org.yqian.job.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yqian.job.entity.JobEntity;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<JobEntity, Integer> {

    @Query("SELECT JOB FROM (SELECT JOB FROM JOB  WHERE STATUS='A' ORDER BY BIDCOUNT DESC) WHERE ROWNUM <= 10")
    public List<JobEntity> findMostActiveJobs();
}
