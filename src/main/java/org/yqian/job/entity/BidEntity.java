package org.yqian.job.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "bid")
public class BidEntity {

    @Id
    private int id;

    private String name;

    private String email;
}
