package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserImageEntity is a Querydsl query type for UserImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserImageEntity extends EntityPathBase<UserImageEntity> {

    private static final long serialVersionUID = 1749259421L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserImageEntity userImageEntity = new QUserImageEntity("userImageEntity");

    public final DateTimePath<java.sql.Timestamp> createTimestamp = createDateTime("createTimestamp", java.sql.Timestamp.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUUID = createString("imageUUID");

    public final StringPath originalFileName = createString("originalFileName");

    public final DateTimePath<java.sql.Timestamp> updateTimestamp = createDateTime("updateTimestamp", java.sql.Timestamp.class);

    public final QUserEntity user;

    public QUserImageEntity(String variable) {
        this(UserImageEntity.class, forVariable(variable), INITS);
    }

    public QUserImageEntity(Path<? extends UserImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserImageEntity(PathMetadata metadata, PathInits inits) {
        this(UserImageEntity.class, metadata, inits);
    }

    public QUserImageEntity(Class<? extends UserImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

