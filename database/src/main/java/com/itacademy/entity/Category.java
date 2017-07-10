package com.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@ToString(callSuper = true, exclude = {"blogs"})
@NoArgsConstructor
public class Category extends BaseEntity {

    @Column(name = "name")
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private EnumCategory enumCategory;

    @ManyToMany(mappedBy = "categories")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Getter
    @Setter
    private Set<Blog> blogs = new HashSet<>();


    public Category(EnumCategory enumCategory) {
        this.enumCategory = enumCategory;
    }
}
