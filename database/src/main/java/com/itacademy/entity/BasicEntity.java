package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;


@MappedSuperclass
@ToString
public abstract class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

}