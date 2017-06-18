package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Tom on 11.06.2017.
 */
@Entity
@Table(name = "flashmobs")
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "event_id")
public class Flashmob extends Event {

    @Getter
    @Setter
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EnumFlashmobType flashmobType;
}
