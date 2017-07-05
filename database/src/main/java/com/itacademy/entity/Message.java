package com.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@ToString(callSuper = true)
@NoArgsConstructor
public class Message extends BaseEntity {

    @Column(name = "text")
    @Getter
    @Setter
    private String text;

    @Column(name = "creation_date")
    @Getter
    @Setter
    private LocalDateTime creationDate = LocalDateTime.now();

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_receiver_id")
    private SystemUser userSender;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_sender_id")
    private SystemUser userReceiver;

    public Message(String text, SystemUser userSender, SystemUser userReceiver) {
        this.text = text;
        this.userSender = userSender;
        this.userReceiver = userReceiver;
    }
}
