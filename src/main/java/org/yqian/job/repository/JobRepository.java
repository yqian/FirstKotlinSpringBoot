package org.yqian.job.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yqian.job.entity.JobEntity;

@Repository
public interface JobRepository extends CrudRepository<JobEntity, Integer> {
}
