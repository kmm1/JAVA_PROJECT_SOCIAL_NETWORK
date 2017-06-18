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
public class Comment extends BasicEntity {

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
    private LocalDateTime creationDate;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

//////??????????????????????????????????
    //    @OneToOne
//    @JoinColumn (name = "parent_id")
//    private Comment comment;
}

