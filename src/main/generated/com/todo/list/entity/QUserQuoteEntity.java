package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserQuoteEntity is a Querydsl query type for UserQuoteEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserQuoteEntity extends EntityPathBase<UserQuoteEntity> {

    private static final long serialVersionUID = 333581566L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserQuoteEntity userQuoteEntity = new QUserQuoteEntity("userQuoteEntity");

    public final StringPath author = createString("author");

    public final DateTimePath<java.sql.Timestamp> creaTimestamp = createDateTime("creaTimestamp", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.todo.list.entity.base.Publish> isPublish = createEnum("isPublish", com.todo.list.entity.base.Publish.class);

    public final StringPath quote = createString("quote");

    public final NumberPath<Long> recommand = createNumber("recommand", Long.class);

    public final DateTimePath<java.sql.Timestamp> updateTimestamp = createDateTime("updateTimestamp", java.sql.Timestamp.class);

    public final QUserEntity user;

    public QUserQuoteEntity(String variable) {
        this(UserQuoteEntity.class, forVariable(variable), INITS);
    }

    public QUserQuoteEntity(Path<? extends UserQuoteEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserQuoteEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserQuoteEntity(PathMetadata metadata, PathInits inits) {
        this(UserQuoteEntity.class, metadata, inits);
    }

    public QUserQuoteEntity(Class<? extends UserQuoteEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

