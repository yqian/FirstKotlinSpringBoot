package org.yqian.job.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yqian.job.entity.BidEntity;

@Repository
public interface BidRepository extends CrudRepository<BidEntity, Integer> {
}
