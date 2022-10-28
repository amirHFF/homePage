package com.homepage.home.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class SuperEntity {

    @Transient
    private long id;

    @Column(length = 10 , nullable = false)
    private String insertUser;
    @Column(length = 3 , nullable = false)
    private long version;
    @Column(nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date insertTime;
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updateTime;


}
