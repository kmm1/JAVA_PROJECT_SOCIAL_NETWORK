package com.itacademy.entity;

import lombok.*;


import javax.persistence.*;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
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
