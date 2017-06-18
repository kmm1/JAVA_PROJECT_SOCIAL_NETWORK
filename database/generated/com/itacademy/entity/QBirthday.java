package com.itacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBirthday is a Querydsl query type for Birthday
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QBirthday extends BeanPath<Birthday> {

    private static final long serialVersionUID = -25813618L;

    public static final QBirthday birthday = new QBirthday("birthday");

    public final NumberPath<Integer> dayOfBirth = createNumber("dayOfBirth", Integer.class);

    public final NumberPath<Integer> monthOfBirth = createNumber("monthOfBirth", Integer.class);

    public final NumberPath<Integer> yearOfBirth = createNumber("yearOfBirth", Integer.class);

    public QBirthday(String variable) {
        super(Birthday.class, forVariable(variable));
    }

    public QBirthday(Path<? extends Birthday> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBirthday(PathMetadata metadata) {
        super(Birthday.class, metadata);
    }

}

