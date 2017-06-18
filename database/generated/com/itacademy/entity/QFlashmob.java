package com.itacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFlashmob is a Querydsl query type for Flashmob
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFlashmob extends EntityPathBase<Flashmob> {

    private static final long serialVersionUID = 530394145L;

    public static final QFlashmob flashmob = new QFlashmob("flashmob");

    public final QEvent _super = new QEvent(this);

    public final EnumPath<EnumFlashmobType> flashmobType = createEnum("flashmobType", EnumFlashmobType.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    public QFlashmob(String variable) {
        super(Flashmob.class, forVariable(variable));
    }

    public QFlashmob(Path<? extends Flashmob> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFlashmob(PathMetadata metadata) {
        super(Flashmob.class, metadata);
    }

}

