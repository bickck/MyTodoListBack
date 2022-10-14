package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTodoCommentEntity is a Querydsl query type for TodoCommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodoCommentEntity extends EntityPathBase<TodoCommentEntity> {

    private static final long serialVersionUID = 1284834886L;

    public static final QTodoCommentEntity todoCommentEntity = new QTodoCommentEntity("todoCommentEntity");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.sql.Timestamp> createTimeStamp = createDateTime("createTimeStamp", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> updateTimeStamp = createDateTime("updateTimeStamp", java.sql.Timestamp.class);

    public QTodoCommentEntity(String variable) {
        super(TodoCommentEntity.class, forVariable(variable));
    }

    public QTodoCommentEntity(Path<? extends TodoCommentEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTodoCommentEntity(PathMetadata metadata) {
        super(TodoCommentEntity.class, metadata);
    }

}

