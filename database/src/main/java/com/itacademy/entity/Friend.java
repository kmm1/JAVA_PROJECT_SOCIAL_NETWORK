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
    private User userSender;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "friend_two")
    private User userReceiver;

    public Friend(User userSender, User userReceiver, String status) {
        this.userSender = userSender;
        this.userReceiver = userReceiver;
        this.status = status;
    }
}
