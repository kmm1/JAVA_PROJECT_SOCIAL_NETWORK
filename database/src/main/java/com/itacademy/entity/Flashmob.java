package com.itacademy.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


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

    @Getter
    @Setter
    @Column(name = "about_event")
    private String aboutEvent;


}
