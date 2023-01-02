package com.todo.list.util.uuid;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.procedure.ProcedureCall;

public class MessageUUIDGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
		CommonUUID commonUUID = new CommonUUID();
		
		return commonUUID.generatorCommentUUID();
	}

}
