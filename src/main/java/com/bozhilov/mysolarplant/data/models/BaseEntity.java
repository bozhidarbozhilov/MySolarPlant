package com.bozhilov.mysolarplant.data.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(generator="uuid-string")
    @GenericGenerator(name="uuid-string", strategy="org.hibernate.id.UUIDGenerator")
    @Column(name="id", nullable=false, updatable = false, unique = true)
    private String id;
}

