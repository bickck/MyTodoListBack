package com.todo.list.util.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.todo.list.util.validation.PublishValidImpl;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = { PublishValidImpl.class })
public @interface PublishType {

	String message() default "유효하지 않는 문자열입니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
