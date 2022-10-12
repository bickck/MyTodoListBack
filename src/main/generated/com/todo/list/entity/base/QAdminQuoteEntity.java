package com.todo.list.entity.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdminQuoteEntity is a Querydsl query type for AdminQuoteEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdminQuoteEntity extends EntityPathBase<AdminQuoteEntity> {

    private static final long serialVersionUID = 84748599L;

    public static final QAdminQuoteEntity adminQuoteEntity = new QAdminQuoteEntity("adminQuoteEntity");

    public final StringPath author = createString("author");

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath quote = createString("quote");

    public QAdminQuoteEntity(String variable) {
        super(AdminQuoteEntity.class, forVariable(variable));
    }

    public QAdminQuoteEntity(Path<? extends AdminQuoteEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdminQuoteEntity(PathMetadata metadata) {
        super(AdminQuoteEntity.class, metadata);
    }

}

