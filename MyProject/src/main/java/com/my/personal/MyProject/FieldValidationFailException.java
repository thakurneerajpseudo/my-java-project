package com.my.personal.MyProject;

import java.util.Map;

import org.springframework.validation.BindingResult;

public class FieldValidationFailException extends RuntimeException{
	 private BindingResult bindingResult;

	public FieldValidationFailException(Map<String, String> errors, BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
	public FieldValidationFailException(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
	
	
	
}
