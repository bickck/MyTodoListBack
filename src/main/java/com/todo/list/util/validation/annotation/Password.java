package com.todo.list.util.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.todo.list.util.validation.PasswordValidatorImpl;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordValidatorImpl.class})
public @interface Password {

	String message() default "비밀번호가 올바르지 않습니다.";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
