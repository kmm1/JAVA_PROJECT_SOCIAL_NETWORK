package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.time.*;

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
    private LocalDateTime creationDate= LocalDateTime.now();

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_receiver_id")
    private User userSender;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_sender_id")
    private User userReceiver;

    public Message(String text, User userSender, User userReceiver) {
        this.text = text;
        this.userSender = userSender;
        this.userReceiver = userReceiver;
    }
}
