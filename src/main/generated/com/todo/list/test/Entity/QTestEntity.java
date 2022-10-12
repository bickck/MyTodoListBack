package com.todo.list.test.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTestEntity is a Querydsl query type for TestEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTestEntity extends EntityPathBase<TestEntity> {

    private static final long serialVersionUID = 29300761L;

    public static final QTestEntity testEntity = new QTestEntity("testEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath testArg1 = createString("testArg1");

    public final StringPath testArg2 = createString("testArg2");

    public final StringPath testArg3 = createString("testArg3");

    public final StringPath testArg4 = createString("testArg4");

    public final NumberPath<Long> testArg5 = createNumber("testArg5", Long.class);

    public final NumberPath<Long> testArg6 = createNumber("testArg6", Long.class);

    public final TimePath<java.sql.Time> testArg7 = createTime("testArg7", java.sql.Time.class);

    public QTestEntity(String variable) {
        super(TestEntity.class, forVariable(variable));
    }

    public QTestEntity(Path<? extends TestEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTestEntity(PathMetadata metadata) {
        super(TestEntity.class, metadata);
    }

}

