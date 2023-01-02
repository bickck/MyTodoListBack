package com.todo.list.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMessageEntity is a Querydsl query type for MessageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessageEntity extends EntityPathBase<MessageEntity> {

    private static final long serialVersionUID = 23446932L;

    public static final QMessageEntity messageEntity = new QMessageEntity("messageEntity");

    public final StringPath from = createString("from");

    public final StringPath message = createString("message");

    public final StringPath to = createString("to");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath uuid = createString("uuid");

    public QMessageEntity(String variable) {
        super(MessageEntity.class, forVariable(variable));
    }

    public QMessageEntity(Path<? extends MessageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessageEntity(PathMetadata metadata) {
        super(MessageEntity.class, metadata);
    }

}

