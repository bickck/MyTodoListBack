package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodoEntity is a Querydsl query type for TodoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodoEntity extends EntityPathBase<TodoEntity> {

    private static final long serialVersionUID = -1069704897L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodoEntity todoEntity = new QTodoEntity("todoEntity");

    public final StringPath content = createString("content");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final NumberPath<Long> heart = createNumber("heart", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.todo.list.entity.base.Publish> isPublish = createEnum("isPublish", com.todo.list.entity.base.Publish.class);

    public final DateTimePath<java.sql.Timestamp> lastUpdate = createDateTime("lastUpdate", java.sql.Timestamp.class);

    public final StringPath title = createString("title");

    public final QUserEntity user;

    public QTodoEntity(String variable) {
        this(TodoEntity.class, forVariable(variable), INITS);
    }

    public QTodoEntity(Path<? extends TodoEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodoEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodoEntity(PathMetadata metadata, PathInits inits) {
        this(TodoEntity.class, metadata, inits);
    }

    public QTodoEntity(Class<? extends TodoEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

