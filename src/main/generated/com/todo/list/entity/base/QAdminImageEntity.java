package com.todo.list.entity.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdminImageEntity is a Querydsl query type for AdminImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdminImageEntity extends EntityPathBase<AdminImageEntity> {

    private static final long serialVersionUID = 1500426454L;

    public static final QAdminImageEntity adminImageEntity = new QAdminImageEntity("adminImageEntity");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath originalFileName = createString("originalFileName");

    public QAdminImageEntity(String variable) {
        super(AdminImageEntity.class, forVariable(variable));
    }

    public QAdminImageEntity(Path<? extends AdminImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdminImageEntity(PathMetadata metadata) {
        super(AdminImageEntity.class, metadata);
    }

}

