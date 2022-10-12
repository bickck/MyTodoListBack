package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserTodoEntity is a Querydsl query type for UserTodoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserTodoEntity extends EntityPathBase<UserTodoEntity> {

    private static final long serialVersionUID = 2128000106L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserTodoEntity userTodoEntity = new QUserTodoEntity("userTodoEntity");

    public final StringPath content = createString("content");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final NumberPath<Long> heart = createNumber("heart", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.todo.list.entity.base.Publish> isPublish = createEnum("isPublish", com.todo.list.entity.base.Publish.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdate = createDateTime("lastUpdate", java.sql.Timestamp.class);

    public final StringPath title = createString("title");

    public final QUserEntity user;

    public QUserTodoEntity(String variable) {
        this(UserTodoEntity.class, forVariable(variable), INITS);
    }

    public QUserTodoEntity(Path<? extends UserTodoEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserTodoEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserTodoEntity(PathMetadata metadata, PathInits inits) {
        this(UserTodoEntity.class, metadata, inits);
    }

    public QUserTodoEntity(Class<? extends UserTodoEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

