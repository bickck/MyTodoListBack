package com.todo.list.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.todo.list.util.validation.annotation.Password;

public class PasswordValidatorImpl implements ConstraintValidator<Password, String> {

	String testReg = "^(?=.*[a-z])(?=.[1-9]).{8,20}$";

	String reg = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$";

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		Pattern pattern = Pattern.compile(testReg);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
}
