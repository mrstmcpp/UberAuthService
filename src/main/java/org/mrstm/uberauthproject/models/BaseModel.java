package org.mrstm.uberauthproject.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "createdAt" ,nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate // get when field was created
    protected Date createdAt;

    @Column(name = "updatedAt" ,nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate //get when field is modified
    protected Date updatedAt;
}
