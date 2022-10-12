package com.todo.list.entity.log;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventLogRecordEntity is a Querydsl query type for EventLogRecordEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventLogRecordEntity extends EntityPathBase<EventLogRecordEntity> {

    private static final long serialVersionUID = 1202851422L;

    public static final QEventLogRecordEntity eventLogRecordEntity = new QEventLogRecordEntity("eventLogRecordEntity");

    public final StringPath dateOfOccurrence = createString("dateOfOccurrence");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath locationOfOccurrence = createString("locationOfOccurrence");

    public final StringPath logMessage = createString("logMessage");

    public final StringPath methodOfOccurrence = createString("methodOfOccurrence");

    public QEventLogRecordEntity(String variable) {
        super(EventLogRecordEntity.class, forVariable(variable));
    }

    public QEventLogRecordEntity(Path<? extends EventLogRecordEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventLogRecordEntity(PathMetadata metadata) {
        super(EventLogRecordEntity.class, metadata);
    }

}

