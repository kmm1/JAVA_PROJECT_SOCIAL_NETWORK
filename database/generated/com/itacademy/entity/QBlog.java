package com.itacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlog is a Querydsl query type for Blog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBlog extends EntityPathBase<Blog> {

    private static final long serialVersionUID = -329704653L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlog blog = new QBlog("blog");

    public final QBasicEntity _super = new QBasicEntity(this);

    public final SetPath<Category, QCategory> categories = this.<Category, QCategory>createSet("categories", Category.class, QCategory.class, PathInits.DIRECT2);

    public final SetPath<Comment, QComment> comments = this.<Comment, QComment>createSet("comments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> creationDate = createDateTime("creationDate", java.time.LocalDateTime.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath text = createString("text");

    public final StringPath title = createString("title");

    public final QUser user;

    public QBlog(String variable) {
        this(Blog.class, forVariable(variable), INITS);
    }

    public QBlog(Path<? extends Blog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlog(PathMetadata metadata, PathInits inits) {
        this(Blog.class, metadata, inits);
    }

    public QBlog(Class<? extends Blog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

