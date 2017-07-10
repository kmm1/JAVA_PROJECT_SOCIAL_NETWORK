package com.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "blogs")
@ToString(callSuper = true, exclude = {"categories", "comments"})
@NoArgsConstructor
public class Blog extends BaseEntity {

    @Column(name = "title")
    @Getter
    @Setter
    private String title;

    @Column(name = "text")
    @Getter
    @Setter
    private String text;

    @Column(name = "creation_date")
    @Getter
    @Setter
    private LocalDateTime creationDate;

    @Getter
    @Setter
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @ManyToMany
    @JoinTable(name = "blogs_categories",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();


    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private SystemUser user;

    @Getter
    @Setter
    @OneToMany(mappedBy = "blog")
    private Set<Comment> comments = new HashSet<>();

    @Column(name = "version")
    @Version
    @Getter
    @Setter
    private long version;

    public Blog(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Blog(String title, String text, SystemUser user) {
        this.title = title;
        this.text = text;
        this.user = user;
    }
}


