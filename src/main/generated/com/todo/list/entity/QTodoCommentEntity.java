package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodoCommentEntity is a Querydsl query type for TodoCommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodoCommentEntity extends EntityPathBase<TodoCommentEntity> {

    private static final long serialVersionUID = 1284834886L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodoCommentEntity todoCommentEntity = new QTodoCommentEntity("todoCommentEntity");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.sql.Timestamp> createTimeStamp = createDateTime("createTimeStamp", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTodoEntity todo;

    public final DateTimePath<java.sql.Timestamp> updateTimeStamp = createDateTime("updateTimeStamp", java.sql.Timestamp.class);

    public final QUserEntity user;

    public QTodoCommentEntity(String variable) {
        this(TodoCommentEntity.class, forVariable(variable), INITS);
    }

    public QTodoCommentEntity(Path<? extends TodoCommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodoCommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodoCommentEntity(PathMetadata metadata, PathInits inits) {
        this(TodoCommentEntity.class, metadata, inits);
    }

    public QTodoCommentEntity(Class<? extends TodoCommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.todo = inits.isInitialized("todo") ? new QTodoEntity(forProperty("todo"), inits.get("todo")) : null;
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

