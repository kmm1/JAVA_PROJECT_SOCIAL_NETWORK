package com.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@ToString(callSuper = true)
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Column(name = "comment")
    @Getter
    @Setter
    private String comment;

    @Column(name = "parent_id")
    @Getter
    @Setter
    private Long parentId;

    @Column(name = "creation_date")
    @Getter
    @Setter
    private LocalDateTime creationDate = LocalDateTime.now();

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private SystemUser user;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;
}

