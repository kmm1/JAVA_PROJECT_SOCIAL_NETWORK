package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@ToString(callSuper = true ,exclude = {"profile"/*, "blogs"*/})
public class User extends BasicEntity {

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
    private String role;

    @Column(name = "registration_date")
    @Getter
    @Setter
    private LocalDateTime registrationDate;


    @OneToOne(mappedBy = "user")
    @Getter
    @Setter
    private Profile profile;

// TODO mojet ybrat????
//    @Getter
//    @Setter
//    @OneToMany(mappedBy = "user")
//    private Set<Blog> blogs = new HashSet<Blog>();

    public User(String name, String email, String password, String role, Profile profile) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profile = profile;
    }

    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

