package com.addis.safari_interview.safari.Exceptions;


import javax.xml.bind.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	ErrorMessage exceptionHandler(ValidationException e) {
		return new ErrorMessage("400","Candidate already Exists");
	}
	
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	ErrorMessage exceptionHandler2(CandidateNotFoundException e) {
		return new ErrorMessage("400","Record Not found");
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	ErrorMessage exceptionHandler3(InvalidInputException e) {
		return new ErrorMessage("400","Invalid Input");
	}
}
