package org.yqian.job.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Job")
public class JobEntity {

    @Id
    private int jobId;

    private String name;

    private String description;

    private String requirement;

    private String contactPerson;

    private String contactEmail;

    private double bidPrice;

    private int bidCount;

    private Timestamp expiration;
}
