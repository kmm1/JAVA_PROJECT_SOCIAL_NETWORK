package com.itacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBasicEntity is a Querydsl query type for BasicEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBasicEntity extends EntityPathBase<BasicEntity> {

    private static final long serialVersionUID = 836135168L;

    public static final QBasicEntity basicEntity = new QBasicEntity("basicEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QBasicEntity(String variable) {
        super(BasicEntity.class, forVariable(variable));
    }

    public QBasicEntity(Path<? extends BasicEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBasicEntity(PathMetadata metadata) {
        super(BasicEntity.class, metadata);
    }

}

