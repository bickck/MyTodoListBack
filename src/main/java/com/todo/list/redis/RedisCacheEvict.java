package com.todo.list.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCacheEvict {

	String[] value() default "";
	
	String[] cacheNames() default {};
	
	String[] key() default {};
	
}
