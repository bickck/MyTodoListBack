package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodoHeartEntity is a Querydsl query type for TodoHeartEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodoHeartEntity extends EntityPathBase<TodoHeartEntity> {

    private static final long serialVersionUID = -632080403L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodoHeartEntity todoHeartEntity = new QTodoHeartEntity("todoHeartEntity");

    public final DateTimePath<java.sql.Timestamp> createTimestamp = createDateTime("createTimestamp", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QTodoEntity todoEntity;

    public final QUserEntity user;

    public final StringPath uuid = createString("uuid");

    public QTodoHeartEntity(String variable) {
        this(TodoHeartEntity.class, forVariable(variable), INITS);
    }

    public QTodoHeartEntity(Path<? extends TodoHeartEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodoHeartEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodoHeartEntity(PathMetadata metadata, PathInits inits) {
        this(TodoHeartEntity.class, metadata, inits);
    }

    public QTodoHeartEntity(Class<? extends TodoHeartEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.todoEntity = inits.isInitialized("todoEntity") ? new QTodoEntity(forProperty("todoEntity"), inits.get("todoEntity")) : null;
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

