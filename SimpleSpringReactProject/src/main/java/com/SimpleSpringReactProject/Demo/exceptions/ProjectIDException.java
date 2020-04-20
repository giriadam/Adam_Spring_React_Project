package com.SimpleSpringReactProject.Demo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ProjectIDException extends RuntimeException {
	
	
	public ProjectIDException(String message) {
		super(message);
	}
	
}
