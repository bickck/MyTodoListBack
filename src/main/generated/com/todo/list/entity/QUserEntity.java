package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 204252420L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final DateTimePath<java.sql.Timestamp> createTimestamp = createDateTime("createTimestamp", java.sql.Timestamp.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introComment = createString("introComment");

    public final StringPath password = createString("password");

    public final StringPath personalMessageChannelName = createString("personalMessageChannelName");

    public final ListPath<QuoteEntity, QQuoteEntity> quotes = this.<QuoteEntity, QQuoteEntity>createList("quotes", QuoteEntity.class, QQuoteEntity.class, PathInits.DIRECT2);

    public final ListPath<TodoEntity, QTodoEntity> todos = this.<TodoEntity, QTodoEntity>createList("todos", TodoEntity.class, QTodoEntity.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> updateTimestamp = createDateTime("updateTimestamp", java.sql.Timestamp.class);

    public final QUserImageEntity userImageEntity;

    public final StringPath username = createString("username");

    public QUserEntity(String variable) {
        this(UserEntity.class, forVariable(variable), INITS);
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserEntity(PathMetadata metadata, PathInits inits) {
        this(UserEntity.class, metadata, inits);
    }

    public QUserEntity(Class<? extends UserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userImageEntity = inits.isInitialized("userImageEntity") ? new QUserImageEntity(forProperty("userImageEntity"), inits.get("userImageEntity")) : null;
    }

}

