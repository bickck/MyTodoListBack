package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuoteEntity is a Querydsl query type for QuoteEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuoteEntity extends EntityPathBase<QuoteEntity> {

    private static final long serialVersionUID = -11025719L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuoteEntity quoteEntity = new QQuoteEntity("quoteEntity");

    public final StringPath author = createString("author");

    public final DateTimePath<java.sql.Timestamp> createTimestamp = createDateTime("createTimestamp", java.sql.Timestamp.class);

    public final NumberPath<Long> heart = createNumber("heart", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.todo.list.entity.base.Publish> isPublish = createEnum("isPublish", com.todo.list.entity.base.Publish.class);

    public final StringPath quote = createString("quote");

    public final DateTimePath<java.sql.Timestamp> updateTimestamp = createDateTime("updateTimestamp", java.sql.Timestamp.class);

    public final QUserEntity user;

    public QQuoteEntity(String variable) {
        this(QuoteEntity.class, forVariable(variable), INITS);
    }

    public QQuoteEntity(Path<? extends QuoteEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuoteEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuoteEntity(PathMetadata metadata, PathInits inits) {
        this(QuoteEntity.class, metadata, inits);
    }

    public QQuoteEntity(Class<? extends QuoteEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

