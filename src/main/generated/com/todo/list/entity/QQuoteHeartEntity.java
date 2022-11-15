package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuoteHeartEntity is a Querydsl query type for QuoteHeartEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuoteHeartEntity extends EntityPathBase<QuoteHeartEntity> {

    private static final long serialVersionUID = -576228189L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuoteHeartEntity quoteHeartEntity = new QQuoteHeartEntity("quoteHeartEntity");

    public final DateTimePath<java.sql.Timestamp> createTimestamp = createDateTime("createTimestamp", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QQuoteEntity quoteEntity;

    public final QUserEntity user;

    public final StringPath uuid = createString("uuid");

    public QQuoteHeartEntity(String variable) {
        this(QuoteHeartEntity.class, forVariable(variable), INITS);
    }

    public QQuoteHeartEntity(Path<? extends QuoteHeartEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuoteHeartEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuoteHeartEntity(PathMetadata metadata, PathInits inits) {
        this(QuoteHeartEntity.class, metadata, inits);
    }

    public QQuoteHeartEntity(Class<? extends QuoteHeartEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.quoteEntity = inits.isInitialized("quoteEntity") ? new QQuoteEntity(forProperty("quoteEntity"), inits.get("quoteEntity")) : null;
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

