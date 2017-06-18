package com.itacademy.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Birthday {

    @Column(name = "year_of_birth")
    @Getter
    @Setter
    private int yearOfBirth;

    @Column(name = "month_of_birth")
    @Getter
    @Setter
    private int monthOfBirth;

    @Column(name = "day_of_birth")
    @Getter
    @Setter
    private int dayOfBirth;
}