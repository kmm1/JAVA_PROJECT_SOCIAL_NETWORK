package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "friends")
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Friend extends BaseEntity {

    @Column(name = "status")
    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "friend_one")
    private SystemUser userSender;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "friend_two")
    private SystemUser userReceiver;
}
