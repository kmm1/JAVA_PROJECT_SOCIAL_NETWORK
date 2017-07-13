package com.itacademy.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
public class Address {

    @Column(name = "country")
    @Getter
    @Setter
    private String country;

    @Column(name = "city")
    @Getter
    @Setter
    private String city;
}
