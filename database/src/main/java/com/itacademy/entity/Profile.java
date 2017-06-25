package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
@ToString(callSuper = true, exclude = {"user"})
@NoArgsConstructor
@AllArgsConstructor
public class Profile extends BaseEntity {

    @Column(name = "gender")
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private EnumGender gender;

    @Getter
    @Setter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "home_country")),
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
    })
    private Address homeAddress;

    @Getter
    @Setter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "work_country")),
            @AttributeOverride(name = "city", column = @Column(name = "work_city")),
    })
    private Address workAddress;

    @Column(name = "marital_status")
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private EnumMaritalStatus maritalStatus;

    @Getter
    @Setter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "yearOfBirth", column = @Column(name = "year_of_birth")),
            @AttributeOverride(name = "monthOfBirth", column = @Column(name = "month_of_birth")),
            @AttributeOverride(name = "dayOfBirth", column = @Column(name = "day_of_birth"))
    })
    private Birthday birthday;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
