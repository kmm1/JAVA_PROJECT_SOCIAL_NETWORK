package com.itacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfile is a Querydsl query type for Profile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProfile extends EntityPathBase<Profile> {

    private static final long serialVersionUID = 70887544L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfile profile = new QProfile("profile");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBirthday birthday;

    public final EnumPath<EnumGender> gender = createEnum("gender", EnumGender.class);

    public final QAddress homeAddress;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<EnumMaritalStatus> maritalStatus = createEnum("maritalStatus", EnumMaritalStatus.class);

    public final QUser user;

    public final QAddress workAddress;

    public QProfile(String variable) {
        this(Profile.class, forVariable(variable), INITS);
    }

    public QProfile(Path<? extends Profile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProfile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProfile(PathMetadata metadata, PathInits inits) {
        this(Profile.class, metadata, inits);
    }

    public QProfile(Class<? extends Profile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.birthday = inits.isInitialized("birthday") ? new QBirthday(forProperty("birthday")) : null;
        this.homeAddress = inits.isInitialized("homeAddress") ? new QAddress(forProperty("homeAddress")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
        this.workAddress = inits.isInitialized("workAddress") ? new QAddress(forProperty("workAddress")) : null;
    }

}

