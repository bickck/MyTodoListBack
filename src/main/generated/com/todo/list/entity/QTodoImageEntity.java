package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodoImageEntity is a Querydsl query type for TodoImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodoImageEntity extends EntityPathBase<TodoImageEntity> {

    private static final long serialVersionUID = -2124870334L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodoImageEntity todoImageEntity = new QTodoImageEntity("todoImageEntity");

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath originalFileName = createString("originalFileName");

    public final QTodoEntity todoBoard;

    public QTodoImageEntity(String variable) {
        this(TodoImageEntity.class, forVariable(variable), INITS);
    }

    public QTodoImageEntity(Path<? extends TodoImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodoImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodoImageEntity(PathMetadata metadata, PathInits inits) {
        this(TodoImageEntity.class, metadata, inits);
    }

    public QTodoImageEntity(Class<? extends TodoImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.todoBoard = inits.isInitialized("todoBoard") ? new QTodoEntity(forProperty("todoBoard"), inits.get("todoBoard")) : null;
    }

}

