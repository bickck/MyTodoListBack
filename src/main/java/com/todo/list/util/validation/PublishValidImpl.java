package com.todo.list.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.todo.list.entity.base.Publish;
import com.todo.list.util.validation.annotation.PublishType;

public class PublishValidImpl implements ConstraintValidator<PublishType, Publish> {

	@Override
	public boolean isValid(Publish value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub

		if (value == null) {
			return false;
		}

		if (value == Publish.PRIVATE) {
			return true;
		}

		if (value == Publish.PUBLISH) {
			return true;
		}

		return false;
	}

}
