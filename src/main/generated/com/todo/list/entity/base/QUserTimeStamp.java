package com.todo.list.entity.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserTimeStamp is a Querydsl query type for UserTimeStamp
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserTimeStamp extends BeanPath<UserTimeStamp> {

    private static final long serialVersionUID = 757451844L;

    public static final QUserTimeStamp userTimeStamp = new QUserTimeStamp("userTimeStamp");

    public final DateTimePath<java.sql.Timestamp> creaTimestamp = createDateTime("creaTimestamp", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> updateTimestamp = createDateTime("updateTimestamp", java.sql.Timestamp.class);

    public QUserTimeStamp(String variable) {
        super(UserTimeStamp.class, forVariable(variable));
    }

    public QUserTimeStamp(Path<? extends UserTimeStamp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserTimeStamp(PathMetadata metadata) {
        super(UserTimeStamp.class, metadata);
    }

}

