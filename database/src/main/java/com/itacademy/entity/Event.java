package com.itacademy.entity;

import lombok.*;


import javax.persistence.*;

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

}