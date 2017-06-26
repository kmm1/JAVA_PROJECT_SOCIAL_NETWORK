package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.time.*;

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

    @Column(name = "role",  columnDefinition = "user")
    @Getter
    @Setter ()
    private String role;

    @Column(name = "registration_date")
    @Getter
    @Setter
    private LocalDateTime registrationDate;


    @OneToOne(mappedBy = "user")
    @Getter
    @Setter
    private Profile profile;
}

