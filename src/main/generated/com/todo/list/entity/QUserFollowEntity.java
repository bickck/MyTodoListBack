package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserFollowEntity is a Querydsl query type for UserFollowEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserFollowEntity extends EntityPathBase<UserFollowEntity> {

    private static final long serialVersionUID = 886990677L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserFollowEntity userFollowEntity = new QUserFollowEntity("userFollowEntity");

    public final DateTimePath<java.sql.Timestamp> createTimestamp = createDateTime("createTimestamp", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUserEntity userEntity;

    public final ComparablePath<java.util.UUID> uuid = createComparable("uuid", java.util.UUID.class);

    public QUserFollowEntity(String variable) {
        this(UserFollowEntity.class, forVariable(variable), INITS);
    }

    public QUserFollowEntity(Path<? extends UserFollowEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserFollowEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserFollowEntity(PathMetadata metadata, PathInits inits) {
        this(UserFollowEntity.class, metadata, inits);
    }

    public QUserFollowEntity(Class<? extends UserFollowEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity"), inits.get("userEntity")) : null;
    }

}

