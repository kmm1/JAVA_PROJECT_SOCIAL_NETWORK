package com.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor
@ToString(callSuper = true, exclude = {"profile"})
public class User extends BaseEntity {

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @Column(name = "password")
    @Getter
    @Setter
    private String password;

    @Column(name = "role")
    @Getter
    @Setter
    private String role = "user";

    @Column(name = "registration_date")
    @Getter
    @Setter
    private LocalDateTime registrationDate;


    @OneToOne(mappedBy = "user")
    @Getter
    @Setter
    private Profile profile;
}

