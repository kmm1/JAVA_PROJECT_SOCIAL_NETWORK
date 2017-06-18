package com.itacademy.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@ToString(callSuper = true, exclude = {"blogs"})
@NoArgsConstructor
public class Category extends BasicEntity {

    @Column(name = "name")
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private EnumCategory enumCategory;

    @ManyToMany(mappedBy = "categories")
    @Getter
    @Setter
    private Set<Blog> blogs = new HashSet<>();

    public Category(EnumCategory enumCategory) {
        this.enumCategory = enumCategory;
    }
}
