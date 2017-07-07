package com.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@ToString(callSuper = true)
public abstract class Event extends BaseEntity {

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "holding_date")
    @Getter
    @Setter
    private String holdingDate;

    @Column(name = "creation_date")
    @Getter
    @Setter
    private LocalDateTime CreationDate = LocalDateTime.now();

    @Column(name = "version")
    @Version
    @Getter
    @Setter
    private long version;

}